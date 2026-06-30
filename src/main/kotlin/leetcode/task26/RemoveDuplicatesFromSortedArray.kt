package org.example.leetcode.task26

fun main() {
    val nums = intArrayOf(1, 1, 1, 3, 3, 4, 4, 4, 5, 5)
        .sorted()
        .toIntArray()

    println("Input array: ${nums.joinToString(", ")}")
    println("Unique count: ${removeDuplicates(nums)}")
    println("Input array: ${nums.joinToString(", ")}")
}

fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var write = 1
    var read = 1

    while (read < nums.size) {
        if (nums[read] != nums[read - 1]) {
            nums[write] = nums[read]
            write++
        }
        read++
    }

    return write
}