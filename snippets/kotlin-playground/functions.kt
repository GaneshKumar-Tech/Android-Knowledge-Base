fun main() {
    greet("Ganesh")
    println("Sum: ${add(5, 3)}")

    val result = multiply(4, 2)
    println("Multiply: $result")
}

// Simple function
fun greet(name: String) {
    println("Hello, $name!")
}

// Function with return type
fun add(a: Int, b: Int): Int {
    return a + b
}

// Single expression function
fun multiply(a: Int, b: Int) = a * b

// Default arguments
fun printMessage(message: String = "Default Message") {
    println(message)
}

// Named arguments
fun userInfo(name: String, age: Int) {
    println("Name: $name, Age: $age")
}