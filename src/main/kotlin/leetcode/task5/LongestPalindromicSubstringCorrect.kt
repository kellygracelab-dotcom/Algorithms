package org.example.leetcode.task5

// Longest Palindromic Substring
// LeetCode Task 5
// Level: Medium
// Data: 10/29/2024
// Time: 135 minutes
fun main() {
    println(longestPalindrome("badbsb"))
}

fun longestPalindrome(s: String): String {
    if (s.isEmpty()) return ""
    var result = s.substring(0, 1)

    for (i in s.indices) {
        val oddPalindrome = expandAroundCenter(s, i, i)
        val evenPalindrome = expandAroundCenter(s, i, i + 1)

        if (oddPalindrome.length > result.length) {
            result = oddPalindrome
        }
        if (evenPalindrome.length > result.length) {
            result = evenPalindrome
        }
    }
    return result
}

fun expandAroundCenter(s: String, left: Int, right: Int): String {
    var l = left
    var r = right
    while (l >= 0 && r < s.length && s[l] == s[r]) {
        l--
        r++
    }
    println(s.substring(l + 1, r))
    return s.substring(l + 1, r)
}