package org.example.leetcode.task4

// Median of Two Sorted Arrays
// LeetCode Task 4
// Level: Hard
// Data: 10/22/2024
// Time: 45 minutes
fun main() {
    val nums1 = intArrayOf(1, 3)
    val nums2 = intArrayOf(2, 3, 5, 6, 8)

    println(findMedianSortedArrays(nums1, nums2))
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val mixedArray = (nums1 + nums2).sortedArray()
    return if (mixedArray.size % 2 == 0) {
        val firstNumber = mixedArray[mixedArray.size / 2 - 1]
        val secondNumber = mixedArray[mixedArray.size / 2]
        if (firstNumber == secondNumber) {
            firstNumber.toDouble()
        } else {
            ((firstNumber + secondNumber) / 2.0)
        }
    } else {
        mixedArray[mixedArray.size / 2].toDouble()
    }
}