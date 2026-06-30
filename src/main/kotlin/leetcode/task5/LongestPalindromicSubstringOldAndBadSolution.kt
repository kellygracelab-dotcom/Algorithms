package org.example.leetcode.task5

// Longest Palindromic Substring
// LeetCode Task 5
// Level: Medium
// Data: 10/29/2024
// Time: 65 minutes
fun main() {
    println(longestPalindromeOld("bdbdasdadddddddddddddddddddddddddddddda"))
}

// Bad solution using O(n^3)
// In this solution I used "sliding window", but it's so slow decision
fun longestPalindromeOld(s: String): String {
    if (s.isEmpty()) return ""
    var result = s.substring(0, 1)

    for (windowSize in 2..s.length) {
        var i = 0
        while (i + windowSize <= s.length) {
            val substring = s.substring(i, i + windowSize)
            if (isPalindromeOld(substring) && substring.length > result.length) {
                result = substring
            }
            i++
        }
    }

    return result
}

fun isPalindromeOld(s: String): Boolean {
    val chars = s.toCharArray()
    var leftPoz: Int = chars.size / 2 - 1
    var rightPoz: Int = chars.size / 2 + (chars.size % 2)

    while (leftPoz >= 0) {
        val leftChar = chars[leftPoz]
        val rightChar = chars[rightPoz]

        if (leftChar != rightChar) {
            return false
        }
        leftPoz--
        rightPoz++
    }

    return true
}