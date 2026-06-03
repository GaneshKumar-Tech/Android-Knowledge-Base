package kotlin_playground

import kotlin.text.iterator

fun main(){
    println(isPalindrome("madam"))
    println(isPalindrome_Manual("madam"))
    println(stringReverse("Ganesh"))
}

fun isPalindrome(str: String): Boolean {
    return str == str.reversed()
}

fun isPalindrome_Manual(str: String): Boolean {
    var result = ""
    for (s in str){
        result = s + result
    }
    return result == str
}

fun stringReverse(str: String): String {
    //return str.reversed()
    var result = ""
    for (s in str) {
        result = s + result
    }
    return result
}