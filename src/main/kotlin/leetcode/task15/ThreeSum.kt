package org.example.leetcode.task15

// 3Sum
// LeetCode Task 15
// Level: Medium
// Data: 3/9/2025
// Time: 90 minutes
fun main() {
    val array = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(threeSum(array))
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val sortedNums = nums.sorted()

    if (sortedNums.size < 3 ||
        sortedNums.size > 3000 ||
        sortedNums[0] > 0 ||
        sortedNums[sortedNums.size - 1] < 0
    ) {
        return emptyList()
    }

    val result = mutableListOf<List<Int>>()

    for (firstIndex in 0 until sortedNums.size - 2) {
        if (firstIndex > 0 && sortedNums[firstIndex] == sortedNums[firstIndex - 1]) {
            continue
        }

        var left = firstIndex + 1
        var right = sortedNums.size - 1

        while (left < right) {
            val sum = sortedNums[firstIndex] + sortedNums[left] + sortedNums[right]
            when {
                sum == 0 -> {
                    result.add(listOf(sortedNums[firstIndex], sortedNums[left], sortedNums[right]))
                    while (left < right && sortedNums[left] == sortedNums[left + 1]) {
                        left++
                    }
                    while (left < right && sortedNums[right] == sortedNums[right - 1]) {
                        right--
                    }

                    left++
                    right--
                }

                sum < 0 -> left++
                else -> right--
            }
        }
    }
    return result
}