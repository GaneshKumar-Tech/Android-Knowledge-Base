package kotlin_playground

fun main() {
    //FizzBuzz — Print numbers from 1 to 20, but print "Fizz" for multiples of 3, "Buzz" for multiples of 5,
    // and "FizzBuzz" for multiples of both
    for (i in 1..20){
        //println(i)
        if(i % 3 == 0 && i % 5 == 0){
            println("FizzBuzz")
        }else if(i % 3 == 0){
            println("Fizz")
        }else if(i % 5 == 0){
            println("Buzz")
        }else {
            println(i)
        }
    }
}