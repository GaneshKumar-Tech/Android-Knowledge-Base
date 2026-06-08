# 03 — Flow & Lifecycle

**Topic:** StateFlow, Fragment Lifecycle, Flow Collection  
**Bugs:** 2 | **Score:** 2/2 ✅

---

## ❌ Buggy Code

```kotlin
class MyFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {                    // Bug 1 — wrong scope/lifecycle
            viewModel.uiState.collect { state ->
                updateUI(state)
            }
        }
    }

    private fun updateUI(state: String) {
        println("UI updated: $state")
    }
}

class MyViewModel : ViewModel() {
    val uiState = MutableStateFlow("Loading")     // Bug 2 — exposed mutable state

    fun updateState() {
        viewModelScope.launch {
            delay(1000)
            uiState.value = "Done"
        }
    }
}
```

---

## 🐛 Bugs

### Bug 1 — `lifecycleScope.launch` without `repeatOnLifecycle`
`lifecycleScope.launch` keeps collecting even when the Fragment is in the **background**  
(e.g. another Fragment is on top). This causes:
- Wasted resources
- Potential crashes updating destroyed views

Fix: use `repeatOnLifecycle(Lifecycle.State.STARTED)` which **auto-cancels** when stopped  
and **resumes** when started again.

### Bug 2 — Exposing `MutableStateFlow` directly
Exposing mutable state publicly breaks encapsulation. Any class can modify `uiState.value`.  
Fix: private mutable, public immutable.

---

## ✅ Fixed Code

```kotlin
class MyFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {  // ✅
                viewModel.uiState.collect { state ->
                    updateUI(state)
                }
            }
        }
    }

    private fun updateUI(state: String) {
        println("UI updated: $state")
    }
}

class MyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow("Loading")   // ✅ private mutable
    val uiState: StateFlow<String> = _uiState.asStateFlow()  // ✅ public immutable

    fun updateState() {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = "Done"                       // ✅ update via private ref
        }
    }
}
```

---

## 💡 Key Takeaways
- Always use `repeatOnLifecycle(STARTED)` for Flow collection in Fragments
- Use `viewLifecycleOwner` (not `this`) in Fragments to bind to view lifecycle
- Never expose `MutableStateFlow` or `MutableLiveData` publicly
- Pattern: `private val _x = MutableStateFlow(...)` + `val x = _x.asStateFlow()`
