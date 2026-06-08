# 04 — Room Database + Coroutines

**Topic:** Room DAO, Repository Pattern, Coroutines  
**Bugs:** 3 | **Score:** ✅ Mastered

---

## ❌ Buggy Code

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>          // Bug 1 — not suspend

    @Insert
    fun insertUser(user: User)            // Bug 1 — not suspend
}

class UserRepository(private val dao: UserDao) {

    fun getUsers(): List<User> {          // Bug 2 — not suspend
        return dao.getAllUsers()
    }

    fun saveUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {  // Bug 3 — unmanaged scope
            dao.insertUser(user)
        }
    }
}

class MyViewModel(private val repository: UserRepository) : ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.Main) {  // Bug 4 — wrong dispatcher
            users.value = repository.getUsers()
        }
    }
}
```

---

## 🐛 Bugs

### Bug 1 — DAO functions not `suspend`
Room requires DAO functions to be `suspend` (or return `Flow`/`LiveData`) to work with coroutines.  
Non-suspend DAO calls on a background thread still block that thread unnecessarily.

### Bug 2 — Repository `getUsers()` not `suspend`
Since `dao.getAllUsers()` is suspend, its caller must also be `suspend`.  
This won't compile otherwise.

### Bug 3 — Raw `CoroutineScope` in Repository
`CoroutineScope(Dispatchers.IO).launch` creates an **unmanaged scope** that:
- Can't be cancelled when ViewModel is cleared
- Causes memory leaks
Fix: make `saveUser` suspend and let the ViewModel's `viewModelScope` manage it.

### Bug 4 — `Dispatchers.Main` for DB work
Database access must happen on `Dispatchers.IO`, not `Main`.

---

## ✅ Fixed Code

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>   // ✅

    @Insert
    suspend fun insertUser(user: User)     // ✅
}

class UserRepository(private val dao: UserDao) {

    suspend fun getUsers(): List<User> {   // ✅ suspend propagated up
        return dao.getAllUsers()
    }

    suspend fun saveUser(user: User) {     // ✅ no rogue CoroutineScope
        dao.insertUser(user)
    }
}

class MyViewModel(private val repository: UserRepository) : ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {    // ✅ IO for DB
            val result = repository.getUsers()
            withContext(Dispatchers.Main) {
                users.value = result               // ✅ setValue on Main
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {                    // ✅ managed scope
            repository.saveUser(user)
        }
    }
}
```

---

## 💡 Key Takeaways
- Room DAO functions must be `suspend` for coroutine use
- `suspend` propagates — if the DAO is suspend, repository must be too
- Never use raw `CoroutineScope` in repositories — use `viewModelScope`
- DB/IO work → `Dispatchers.IO`; UI updates → `Dispatchers.Main`
