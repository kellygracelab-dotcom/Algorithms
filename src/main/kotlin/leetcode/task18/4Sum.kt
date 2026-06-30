package org.example.leetcode.task18

fun main() {
    val nums = intArrayOf(-3, -1, 0, 2, 4, 5)
    val target = 0

    println(fourSum(nums, target))
}

fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    val size = nums.size
    if (size < 4) {
        return emptyList()
    }
    nums.sort()
    val resultList = mutableListOf<List<Int>>()

    for (i in 0 until size - 3) {
        for (j in i + 1 until size - 2) {
            var left = j + 1
            var right = size - 1



            while (left < right) {
                val sum =
                    nums[i].toLong() + nums[j].toLong() + nums[left].toLong() + nums[right].toLong()
                when {
                    sum == target.toLong() -> {
                        if (!resultList.contains(
                                listOf(
                                    nums[i],
                                    nums[j],
                                    nums[left],
                                    nums[right]
                                )
                            )
                        ) {
                            resultList.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                        }
                        while (left < right && nums[left] == nums[left + 1]) left++
                        while (left < right && nums[right] == nums[right - 1]) right--
                        left++
                        right--
                    }

                    sum < target.toLong() -> left++
                    else -> right--
                }
            }
        }
    }

    return resultList
}