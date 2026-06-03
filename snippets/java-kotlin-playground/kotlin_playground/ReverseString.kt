package kotlin_playground

fun main() {
    //Reverse a String — Given "Kotlin", print it reversed without using .reversed()
    val name = "Kotlin"
    var output = StringBuilder()
    for (i in name.length-1 downTo 0){
        output.append(name[i])
    }
    println(output)
}