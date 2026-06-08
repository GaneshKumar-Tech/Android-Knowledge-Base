# 01 — ViewModel + Coroutines Basics

**Topic:** ViewModel, LiveData, Coroutines  
**Bugs:** 2 | **Score:** 1/2

---

## ❌ Buggy Code

```kotlin
class MyViewModel : ViewModel() {

    val userList = MutableLiveData<List<String>>()

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.Main) {  // Bug 1
            val users = fetchUsersFromNetwork()
            userList.postValue(users)              // Bug 2
        }
    }

    suspend fun fetchUsersFromNetwork(): List<String> {
        delay(1000)
        return listOf("Alice", "Bob", "Charlie")
    }
}
```

---

## 🐛 Bugs

### Bug 1 — Wrong Dispatcher
`Dispatchers.Main` is the UI thread. Network/suspend work should run on `Dispatchers.IO`.  
Using `Main` for IO work **blocks the UI thread**.

### Bug 2 — `postValue` on Main thread
After fixing Bug 1 and switching context back to Main with `withContext`, you should use  
`userList.value = users` (setValue), not `postValue`.  
- `postValue` → background threads  
- `value =` → Main thread only

---

## ✅ Fixed Code

```kotlin
class MyViewModel : ViewModel() {

    val userList = MutableLiveData<List<String>>()

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {        // ✅ IO for network
            val users = fetchUsersFromNetwork()
            withContext(Dispatchers.Main) {
                userList.value = users                 // ✅ setValue on Main
            }
        }
    }

    suspend fun fetchUsersFromNetwork(): List<String> {
        delay(1000)
        return listOf("Alice", "Bob", "Charlie")
    }
}
```

> **Alternative:** Skip `withContext` and use `userList.postValue(users)` directly on IO thread — also valid.

---

## 💡 Key Takeaways
- Never run network/IO work on `Dispatchers.Main`
- Match `setValue`/`postValue` to which thread you're on
- `viewModelScope` automatically cancels when ViewModel is cleared
