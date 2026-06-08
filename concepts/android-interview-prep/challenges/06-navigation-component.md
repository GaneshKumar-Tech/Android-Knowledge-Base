# 06 — Navigation Component & Fragment BackStack

**Topic:** Navigation Component, Fragment BackStack, Safe Navigation  
**Bugs:** 3 | **Score:** 2/3

---

## ❌ Buggy Code

```kotlin
class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToDetail.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeToDetail(userId = 123)
            findNavController().navigate(action)      // Bug 1 — no double-tap guard
        }

        binding.btnGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
            findNavController().navigate(R.id.settingsFragment)  // Bug 2 — duplicate navigate
        }
    }
}

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()          // Bug 3 — deprecated API 33+
        }

        binding.btnSave.setOnClickListener {
            val result = "Saved: ${args.userId}"
            findNavController().previousBackStackEntry
                ?.savedStateHandle
                ?.set("result", result)
        }
    }
}
```

---

## 🐛 Bugs

### Bug 1 — No double-tap guard on navigation
Rapid taps on `btnGoToDetail` can trigger `navigate()` **twice** before the first navigation  
completes, pushing the destination twice onto the backstack and causing a crash.  
Fix: check `currentDestination` before navigating.

### Bug 2 — Duplicate `navigate()` call
`settingsFragment` is pushed **twice** onto the backstack. The user has to press back  
twice to leave Settings. Remove the duplicate call.

### Bug 3 — `requireActivity().onBackPressed()` deprecated
Deprecated since API 33. Use `findNavController().popBackStack()` instead,  
which is the correct Navigation Component approach.

---

## ✅ Fixed Code

```kotlin
class HomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToDetail.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.homeFragment) {  // ✅ double-tap guard
                val action = HomeFragmentDirections
                    .actionHomeToDetail(userId = 123)
                findNavController().navigate(action)
            }
        }

        binding.btnGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)  // ✅ single call
        }
    }
}

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()         // ✅ not deprecated
        }

        binding.btnSave.setOnClickListener {
            val result = "Saved: ${args.userId}"
            findNavController().previousBackStackEntry
                ?.savedStateHandle
                ?.set("result", result)                // ✅ correct result passing
        }
    }
}
```

---

## 💡 Key Takeaways
- Always guard `navigate()` with a `currentDestination` check to prevent double-tap crashes
- Never call `navigate()` to the same destination twice in a row
- Use `findNavController().popBackStack()` — not `onBackPressed()` (deprecated API 33+)
- Pass results between fragments via `savedStateHandle`, not shared ViewModel or arguments
