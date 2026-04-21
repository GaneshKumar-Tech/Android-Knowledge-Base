fun main() {
    val sum = { a: Int, b: Int -> a + b }
    println("Lambda Sum: ${sum(3, 4)}")

    val list = listOf(1, 2, 3, 4)

    list.forEach { println(it) }

    val doubled = list.map { it * 2 }
    println("Doubled: $doubled")
}