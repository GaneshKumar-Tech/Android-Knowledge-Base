fun main() {
    var name: String? = null

    // Safe call
    println("Length: ${name?.length}")

    // Elvis operator
    val length = name?.length ?: 0
    println("Length with Elvis: $length")

    // Let
    name = "Ganesh"
    name?.let {
        println("Name is not null: $it")
    }

    // !! operator (use carefully)
    val forceLength = name!!.length
    println("Force length: $forceLength")

    // Nullable function example
    printLength(null)
}

fun printLength(text: String?) {
    if (text != null) {
        println("Length: ${text.length}")
    } else {
        println("Text is null")
    }
}