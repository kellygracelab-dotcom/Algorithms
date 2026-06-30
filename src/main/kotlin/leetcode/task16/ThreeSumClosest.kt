package org.example.leetcode.task16

import kotlin.math.abs

// 3Sum Closest
// LeetCode Task 16
// Level: Medium
// Data: 3/28/2025
// Time: 60 minutes
fun main() {
    val a1 = intArrayOf(1, 3, 1, 4, 6, 5, 6)
    val k = 1

    println(threeSumClosest(a1, k))
}

fun threeSumClosest(nums: IntArray, target: Int): Int {
    nums.sort()
    var closestSum = nums[0] + nums[1] + nums[2]

    for (i in 0 until nums.size - 2) {
        var left = i + 1
        var right = nums.size - 1

        while (left < right) {
            val currentSum = nums[i] + nums[left] + nums[right]

            if (abs(currentSum - target) < abs(closestSum - target)) {
                closestSum = currentSum
            }

            when {
                currentSum < target -> left++
                currentSum > target -> right--
                else -> return currentSum
            }
        }
    }

    return closestSum
}