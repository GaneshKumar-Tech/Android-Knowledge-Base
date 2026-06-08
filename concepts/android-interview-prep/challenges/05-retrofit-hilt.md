# 05 — Retrofit + Hilt Dependency Injection

**Topic:** Retrofit, Hilt, Coroutines, Security  
**Bugs:** 3 | **Score:** 2.5/3

---

## ❌ Buggy Code

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides                                          // Bug 1 — missing @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.example.com")         // Bug 2 — HTTP not HTTPS
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides                                          // Bug 1 — missing @Singleton
    fun provideApiService(): ApiService {
        return provideRetrofit().create(ApiService::class.java)  // Bug 3 — calling method directly
    }
}

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>                   // Bug 4 — not suspend, returns Call<>
}

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsers(): List<User> {
        val response = apiService.getUsers().execute() // Bug 4 — .execute() blocks thread
        return response.body() ?: emptyList()
    }
}
```

---

## 🐛 Bugs

### Bug 1 — Missing `@Singleton` on providers
Without `@Singleton`, Hilt creates a **new instance every time** `Retrofit` or `ApiService`  
is injected. This is wasteful — network clients should be singletons.

### Bug 2 — `http` instead of `https`
Android blocks cleartext HTTP traffic by default in production.  
This will cause `java.io.IOException: Cleartext HTTP traffic not permitted`.

### Bug 3 — Calling `provideRetrofit()` directly
Instead of injecting `Retrofit` as a parameter, calling `provideRetrofit()` directly  
**bypasses Hilt's dependency graph** and creates a new, unmanaged instance.

### Bug 4 — `Call<>` + `.execute()` instead of `suspend`
Retrofit supports coroutines natively. Using `Call<>` with `.execute()` **blocks the thread**.  
Use `suspend` + direct return type instead.

---

## ✅ Fixed Code

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton                                         // ✅ single instance
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com")        // ✅ HTTPS
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton                                         // ✅ single instance
    fun provideApiService(retrofit: Retrofit): ApiService {  // ✅ injected parameter
        return retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>                 // ✅ suspend, no Call<>
}

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsers() = apiService.getUsers()     // ✅ clean one-liner
}
```

---

## 💡 Key Takeaways
- Always `@Singleton` for network/DB providers in Hilt
- Inject dependencies as parameters — never call `provide*()` methods directly
- Always use `https` — Android blocks `http` by default
- Retrofit + coroutines: `suspend fun` returning the type directly, no `Call<>` or `.execute()`
