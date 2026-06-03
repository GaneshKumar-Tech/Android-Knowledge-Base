package kotlin_playground

fun main() {
    //Sum of Digits — Given the number 98345, find the sum of its digits
    val number = 98345
    var sum = 0
    /*number.toString().forEach {
        sum += it.digitToInt()
    }*/
    /*for (i in 0 until number.toString().length){
        println(i)
        sum += i
    }*/
    for (i in number.toString()){
        //println(i)
        sum += i.digitToInt()
    }
    println(sum)
}