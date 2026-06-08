# 📱 Android Interview Prep

A personal reference of bug-fixing challenges and Android concepts for HackerRank-style assessments.

---

## 📚 Challenges Index

| # | Topic | Bugs | Score | File |
|---|-------|------|-------|------|
| 01 | [ViewModel + Coroutines Basics](./challenges/01-viewmodel-coroutines-basics.md) | 2 | 1/2 | `01-viewmodel-coroutines-basics.md` |
| 02 | [Coroutine Cancellation & Exception Handling](./challenges/02-coroutine-cancellation-exceptions.md) | 2 | 1/2 | `02-coroutine-cancellation-exceptions.md` |
| 03 | [Flow & Lifecycle](./challenges/03-flow-lifecycle.md) | 2 | 2/2 ✅ | `03-flow-lifecycle.md` |
| 04 | [Room Database + Coroutines](./challenges/04-room-database-coroutines.md) | 3 | ✅ Mastered | `04-room-database-coroutines.md` |
| 05 | [Retrofit + Hilt Dependency Injection](./challenges/05-retrofit-hilt.md) | 3 | 2.5/3 | `05-retrofit-hilt.md` |
| 06 | [Navigation Component & Fragment BackStack](./challenges/06-navigation-component.md) | 3 | 2/3 | `06-navigation-component.md` |

---

## 🧠 Key Concepts Quick Reference

### Dispatchers
| Dispatcher | Use For |
|------------|---------|
| `Dispatchers.Main` | UI updates only |
| `Dispatchers.IO` | Network, Database, File I/O |
| `Dispatchers.Default` | CPU-intensive work |

### LiveData vs StateFlow
| | `LiveData` | `StateFlow` |
|--|------------|-------------|
| Lifecycle aware | ✅ Yes | ❌ No (needs `repeatOnLifecycle`) |
| Initial value | ❌ Not required | ✅ Required |
| Exposed as | `MutableLiveData` / `LiveData` | `MutableStateFlow` / `StateFlow` |

### Golden Rules
- ✅ Always use `viewModelScope` — never raw `CoroutineScope`
- ✅ Network/DB calls → `Dispatchers.IO`
- ✅ UI updates → `Dispatchers.Main` or `withContext(Dispatchers.Main)`
- ✅ Use `postValue` on background threads, `value =` on Main
- ✅ Expose immutable state: private `_uiState`, public `uiState`
- ✅ Use `repeatOnLifecycle(Lifecycle.State.STARTED)` for Flow collection in Fragments
- ✅ Use `supervisorScope` when using `async` to isolate failures
- ✅ Always `https` — Android blocks cleartext HTTP by default
- ✅ Annotate `@Singleton` on Hilt providers to avoid duplicate instances

---

## 📅 Progress Tracker

| Date | Challenges Completed | Notes |
|------|----------------------|-------|
| — | 6 | Initial session — Android Interview Prep |

---

> 💡 **Tip:** Each challenge file contains the buggy code, your fix, and the explanation. Review before assessments!
