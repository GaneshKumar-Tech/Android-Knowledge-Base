package kotlin_playground

fun main() {
    //Given the list of numbers: 3.5, 1.2, 4.8, 2.1, 9.3, 0.7, 6.4, 5.0, 8.6, 7.9
    val numbers = listOf(3.5, 1.2, 4.8, 2.1, 9.3, 0.7, 6.4, 5.0, 8.6, 7.9)
    println("Original: $numbers")
    val ascending = numbers.sorted()
    println("Ascending: "+ascending)
    println("Factory Method Descending: "+numbers.sortedDescending())

    val descending = mutableListOf<Double>()
    for(i in ascending.size-1 downTo 0){
        descending.add(ascending[i])
    }

    println("Manual Descending: $descending")
}
