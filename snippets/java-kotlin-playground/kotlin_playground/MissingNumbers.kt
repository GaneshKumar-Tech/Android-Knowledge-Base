package kotlin_playground

fun main() {
    //Find the Missing Number — Given a list [1, 2, 3, 4, 6, 7, 8, 9, 10], find the missing number
    val numbers = listOf(1, 2, 3, 4, 6, 7, 8, 9, 10)
    numbers.sorted()
    for (i in numbers){
        if (i != numbers[i-1]){
            println(i)
        }
    }
}