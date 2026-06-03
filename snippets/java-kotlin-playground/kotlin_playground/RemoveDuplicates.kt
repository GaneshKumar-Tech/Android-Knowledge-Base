package kotlin_playground

fun main() {
    //Remove Duplicates — Given "programming", print only the unique characters in order of appearance
    val name = "programming"
    val map = LinkedHashMap<Char, Int>()

    var count = 0
    for (i in name){
        if (!map.containsKey(i)){
            map.put(i, count)
            count++
        }
    }

    println(map.keys.joinToString(""))

}

