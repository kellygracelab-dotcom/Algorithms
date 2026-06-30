package org.example.leetcode.task27

fun main() {
    val nums = intArrayOf(3,2,2,3)


    println("Input array: ${nums.joinToString(", ")}")
    println("Unique count: ${removeElement(nums, 3)}")
    println("Input array: ${nums.joinToString(", ")}")
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    if (nums.isEmpty()) return 0
    nums.sort()

    var read = 0
    var index = nums.size - 1

    while (read <= index) {
        if (nums[read] == `val`) {
            nums[read] = nums[index]
            index--
        } else {
            read++
        }
    }

    return index + 1
}
