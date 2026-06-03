package kotlin_playground

fun main() {
    //Anagram Check — Given two strings "listen" and "silent", check if they are anagrams of each other
    val str1 = "listen"
    val str2 = "silent"
    if (str1.lowercase().toCharArray().sorted() == str2.lowercase().toCharArray().sorted()){
        println("Anagram")
    }else{
        println("Not Anagram")
    }
}
