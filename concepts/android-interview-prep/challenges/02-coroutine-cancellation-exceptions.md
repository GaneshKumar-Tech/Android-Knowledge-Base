# 02 — Coroutine Cancellation & Exception Handling

**Topic:** Coroutines, async/await, Exception Handling  
**Bugs:** 2 | **Score:** 1/2

---

## ❌ Buggy Code

```kotlin
class MyViewModel : ViewModel() {

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = async(Dispatchers.IO) {
                    riskyNetworkCall()
                }
                println(result.await())
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    suspend fun riskyNetworkCall(): String {
        delay(500)
        throw IOException("Network failed")
        return "Success"           // Bug 2 — unreachable code
    }
}
```

---

## 🐛 Bugs

### Bug 1 — `async` exception propagates to parent scope
When `async` throws inside `launch`, the exception can propagate and **crash the parent scope**  
before the `try/catch` handles it. The fix is to wrap in `supervisorScope` to isolate failures.

### Bug 2 — Unreachable code after `throw`
`return "Success"` after `throw IOException(...)` is **unreachable** and won't compile cleanly.  
Remove it.

---

## ✅ Fixed Code

```kotlin
class MyViewModel : ViewModel() {

    fun fetchData() {
        viewModelScope.launch {
            supervisorScope {                         // ✅ Isolates async failure
                try {
                    val result = async(Dispatchers.IO) {
                        riskyNetworkCall()
                    }
                    println(result.await())
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
        }
    }

    suspend fun riskyNetworkCall(): String {
        delay(500)
        throw IOException("Network failed")          // ✅ No unreachable code below
    }
}
```

---

## 💡 Key Takeaways
- `async` stores exceptions in the `Deferred` — thrown only at `.await()`
- Without `supervisorScope`, a failing `async` child can cancel the parent `launch`
- `supervisorScope` lets sibling coroutines continue even if one fails
- Remove any code after `throw` — it's unreachable and a code smell
