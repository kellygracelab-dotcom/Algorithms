package org.example.leetcode.task8

// String To Integer
// LeetCode Task 8
// Level: Medium
// Data: 11/20/2024
// Time: 10 minutes (too ease:))
fun main() {
    println(myAtoi("-91283472332"))
}

fun myAtoi(s: String): Int {
    val str = s.trim()
    if (str.isEmpty()) return 0

    var index = 0
    var isNegative = false
    var result = 0

    if (str[index] == '-' || str[index] == '+') {
        isNegative = str[index] == '-'
        index++
    }

    while (index < str.length && str[index].isDigit()) {
        val digit = str[index] - '0'

        if (result > (Int.MAX_VALUE - digit) / 10) {
            return if (isNegative) Int.MIN_VALUE else Int.MAX_VALUE
        }
        result = result * 10 + digit
        index++
    }

    return if (isNegative) -result else result
}
