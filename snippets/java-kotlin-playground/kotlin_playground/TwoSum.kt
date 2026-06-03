package kotlin_playground

fun main(){
    println(twoSum(intArrayOf(2,6,7,11),9).contentToString())
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int,Int>()
    for (i in nums.indices){
        val diff = target - nums[i]
        if (map.containsKey(diff)){
            return intArrayOf(map[diff]!!,i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}