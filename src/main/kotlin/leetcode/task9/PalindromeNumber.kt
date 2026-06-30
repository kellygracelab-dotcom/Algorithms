package org.example.leetcode.task9

// Palindrome Number
// LeetCode Task 9
// Level: Easy
// Data: 11/21/2024
// Time: 15 minutes
fun main() {
    println(isPalindrome(10))
}

fun isPalindrome(x: Int): Boolean {
    val str = x.toString()
    val strLength = str.length
    var left = strLength / 2 - 1
    var right = if (strLength % 2 == 0) strLength / 2 else strLength / 2 + 1

    while (left >= 0) {
        if (str[left] != str[right]) {
            return false
        }
        left--
        right++
    }
    return true
}