package kotlin_playground

fun main(args: Array<String>) {
    //Second Largest — Given the list [3, 1, 7, 5, 9, 2, 8, 4, 6],
    // find the second largest number without using sorting
    //val numbers = listOf(3, 1, 7, 5, 9, 2, 8, 4, 6)
    val numbers = listOf(10, 20, 15)
    //println(numbers.max())
    val max = numbers.max()
    val map = HashMap<Int,Int>()
    //val set = HashSet<Int>()
    for (i in numbers){
        //println(i)
        if (i != max){
            map.put(max- i,i)
            //set.add(i)
        }
    }
    //println(map.values.minOrNull())
    //println(map[map.values.minOrNull()])
    println(map[map.keys.minOrNull()])
    //println(set.maxOrNull())
}