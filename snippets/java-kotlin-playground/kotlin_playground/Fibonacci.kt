package kotlin_playground

fun main(args: Array<String>) {
    //fibonacci Sequence — Print the first 10 numbers of the Fibonacci sequence
    printFibonacci(10)
}

fun printFibonacci(n: Int) {
    var number1 = 0
    var number2 = 1
    repeat(n) {
        println(number1)
        val sum = number1 + number2
        number1 = number2
        number2 = sum
    }
}