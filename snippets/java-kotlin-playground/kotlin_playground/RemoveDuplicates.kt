package kotlin_playground

import kotlin.text.iterator

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

    println(printUniqueChar(name))

    println(name.toList().distinct().joinToString(""))

    val seen = mutableSetOf<Char>()
    val result = StringBuilder()

    for (i in name){
        if (i !in seen){
            seen.add(i)
            result.append(i)
        }
    }

    println(result.toString())
}

fun printUniqueChar(name: String): String{
    var result = StringBuilder()
    for (i in name){
        if (!result.contains(i)){
            result.append(i)
        }
    }
    return result.toString()
}
