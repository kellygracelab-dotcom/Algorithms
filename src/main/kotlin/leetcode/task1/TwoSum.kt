package org.example.leetcode.task1

// Two Sum.
// LeetCode Task 1
// Level: Easy
// Data: 10/21/2024
// Time: 28 minutes
fun main() {
    val nums = intArrayOf(2, 7, 11, 15)
    val target = 26
    val result = twoSum(nums, target)

    println(result.contentToString())
}

// O(n)
// 26 - nums[0] = 24 -> !numMap.contains(24) -> numMap.put(2, 0)
// 26 - nums[1] = 19 -> !numMap.contains(19) -> numMap.put(7, 1)
// 26 - nums[2] = 15 -> !numMap.contains(15) -> numMap.put(11, 2)
// 26 - nums[3] = 11 -> numMap.contains(11)(true) -> return [2, 3] (index which we get by key 11 and current index)
fun twoSum(nums: IntArray, target: Int): IntArray {
    val numMap = mutableMapOf<Int, Int>()

    for (i in nums.indices) {
        val buff = target - nums[i]
        if (numMap.containsKey(buff)) {
            return intArrayOf(numMap[buff]!!, i)
        }
        numMap[nums[i]] = i
    }

    return intArrayOf()
}