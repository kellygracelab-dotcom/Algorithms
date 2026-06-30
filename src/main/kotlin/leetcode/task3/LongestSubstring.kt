package org.example.leetcode.task3

// Longest Substring Without Repeating Characters
// LeetCode Task 3
// Level: Medium
// Data: 10/21/2024
// Time: 1 hour 34 minutes
fun main() {
    val str = "abba"
    println(lengthOfLongestSubstring(str))
}

// for solution we should use sliding window
// Example with "abba"
// Step 1: 'a' -> charsMap = {'a' -> 0}, result = 1
// Step 2: 'b' -> charsMap = {'a' -> 0, 'b' -> 1}, result = 2
// Step 3: 'b' -> charsMap = {'a' -> 0, 'b' -> 2}, result remains 2, i = 2
// Step 4: 'a' -> charsMap = {'a' -> 3, 'b' -> 2}, result remains 2, i = 2
// Final result: 2 (substring "ab")
fun lengthOfLongestSubstring(s: String): Int {
    var result = 0
    val charsMap = mutableMapOf<Char, Int>()
    var i = 0

    for (j in s.indices) {
        if (charsMap.containsKey(s[j])) {
            i = maxOf(charsMap[s[j]]!! + 1, i)
        }
        charsMap[s[j]] = j
        result = maxOf(result, j - i + 1)
    }

    return result
}