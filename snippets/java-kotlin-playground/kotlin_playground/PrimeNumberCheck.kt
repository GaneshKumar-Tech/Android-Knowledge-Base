package kotlin_playground

fun main(args: Array<String>) {
    //println(isPrimeNumber(29))
    val number = 29
    if (isPrimeNumber(number)) println("$number is a Prime Number")
    else println("$number is not a Prime Number")
}

fun isPrimeNumber(number: Int): Boolean {
    if (number < 2) return false

    //println(Math.sqrt(number.toDouble()).toInt())

    // Check every number from 2 up to the square root of 'number'
    for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
        if (number % i == 0) return false
    }

    return true
}
