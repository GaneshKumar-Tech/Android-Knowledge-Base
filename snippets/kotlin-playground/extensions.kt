fun main() {
    val name = "ganesh"
    println(name.capitalizeFirst())

    val number = 5
    println(number.isEven())
}

// Extension function for String
fun String.capitalizeFirst(): String {
    return this.replaceFirstChar { it.uppercase() }
}

// Extension function for Int
fun Int.isEven(): Boolean {
    return this % 2 == 0
}