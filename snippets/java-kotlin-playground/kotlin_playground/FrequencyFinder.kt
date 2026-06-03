package kotlin_playground

fun main() {
    val name = "Discussion"
    val map = HashMap<Char, Int>()
    for (i in name){
        //println(i)
        if (map.containsKey(i)){
            //map[i] = map[i]!! + 1
            map.put(i, map[i]!! + 1)
        }else{
            //map[i] = 1
            map.put(i,1)
        }
    }
    println(map)

    //Count Vowels — Given "Hello World", count how many vowels are in the string

    println("Vowels Count: ${vowelsCount(arg = "Hello World")}")
}

fun vowelsCount(arg: String): Int {
    var count = 0
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    for (i in arg){
        if (vowels.contains(i)){
            count++
        }
    }
    return count
}
