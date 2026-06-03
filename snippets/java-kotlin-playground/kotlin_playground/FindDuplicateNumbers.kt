package kotlin_playground

fun main(){
    //Given the list of number: 2,2,2,5,1,1,1,1,2,2,2,7,9,6,5,5
    //Question: Print the numbers that appears at least twice!
    val list = listOf(2,2,2,5,1,1,1,1,2,2,2,7,9,6,5,5)
    val map = HashMap<Int,Int>()
    for(i in 0 until list.size){
        //println(list[i])
        if(map.containsKey(list[i])){
            //map.put(list[i], map.get(list[i])?.plus(1) ?: 1)
            map.put(list[i], map.get(list[i])!! + 1)
        }else{
            map.put(list[i],1)
        }
    }
    println(""+map.filter{it.value >= 2})
    //println(list.filter { it > 2 })
}