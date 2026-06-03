package kotlin_playground

fun main() {

    val users = listOf(
        User("Charlie", 25),
        User("Alice", 16),
        User("Bob", 30),
        User("Diana", 17),
        User("Eve", 22)
    )

    println(getAdultNames(users))
    val userList = getAdultList(users)

    userList.sortedBy { it.age }.also { println("Sorted by ascending age") }
        .forEach { println("Name: ${it.name}, Age: ${it.age}") }

    userList.sortedByDescending { it.age }.also{ println("Sorted by descending age") }
        .forEach { println("Name: ${it.name}, Age: ${it.age}") }

}

data class User(val name: String, val age: Int)

fun getAdultNames(users: List<User>): List<String> {
    // Your code here
    return users.filter { it.age >= 18 }.map { it.name }.sorted()
}

fun getAdultList(users: List<User>): List<User> {
    // Your code here
    //return users.filter { it.age >= 18 }.sortedBy { it.name }
    return users.filter { it.age >= 18 }
}
