package org.example.leetcode.task11

// Container With Most Water
// LeetCode Task 11
// Level: Medium
// Data: 12/11/2024
// Time: 30 minutess
fun main() {
    val arr = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    println(maxArea(arr))
}

fun maxArea(height: IntArray): Int {
    var lIndex = 0
    var rIndex = height.size - 1
    var result = 0

    while (lIndex < rIndex) {
        val buff = minOf(height[lIndex], height[rIndex])
        val resultBuff = buff * (rIndex - lIndex)
        if (resultBuff > result) {
            result = resultBuff
        }
        if (height[lIndex] > height[rIndex]) {
            rIndex--
        } else if (height[lIndex] < height[rIndex]) {
            lIndex++
        } else {
            rIndex--
            lIndex++
        }
    }

    return result
}
