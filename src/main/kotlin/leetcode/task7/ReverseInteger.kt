package org.example.leetcode.task7

// Reverse Integer
// LeetCode Task 7
// Level: Medium
// Data: 10/11/2024 - 10/11/2024
// Time: 10 minutes (too ease:))
fun main() {
    println(reverse(-123))
}

fun reverse(x: Int): Int {
    var buff = x
    var result = 0

    while (buff != 0) {
        val digit = buff % 10
        if (result > Int.MAX_VALUE / 10 || (result == Int.MAX_VALUE / 10 && digit > 7)) {
            return 0
        }
        if (result < Int.MIN_VALUE / 10 || (result == Int.MIN_VALUE / 10 && digit < -8)) {
            return 0
        }

        result = result * 10 + digit
        buff /= 10
    }

    return result
}
