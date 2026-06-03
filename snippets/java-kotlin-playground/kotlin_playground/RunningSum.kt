package kotlin_playground

fun main(){
    println(runningSum(intArrayOf(3,1,2,10,1)).contentToString())
}

fun runningSum(nums: IntArray): IntArray {
    for (i in 1 until nums.size) {
        nums[i] += nums[i - 1]
    }
    return nums
}