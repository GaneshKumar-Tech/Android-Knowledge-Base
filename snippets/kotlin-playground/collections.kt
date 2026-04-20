fun main() {
    // List
    val numbers = listOf(1, 2, 3, 4, 5)
    println("List: $numbers")

    // Mutable List
    val mutableList = mutableListOf(10, 20)
    mutableList.add(30)
    println("Mutable List: $mutableList")

    // Set (no duplicates)
    val set = setOf(1, 2, 2, 3)
    println("Set: $set")

    // Map
    val map = mapOf("A" to 1, "B" to 2)
    println("Map: $map")

    // Higher-order functions
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even Numbers: $evenNumbers")

    val squared = numbers.map { it * it }
    println("Squared: $squared")

    val sum = numbers.reduce { acc, i -> acc + i }
    println("Sum: $sum")
}