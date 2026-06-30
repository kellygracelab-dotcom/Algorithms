package org.example.leetcode.task10

// Regular Expression Matching
// LeetCode Task 10
// Level: Hard
// Data: 12/08/2024
// We need to Use DP in this task
fun main() {
    println(isMatch("aa", "a*b*"))
}

fun isMatch(s: String, p: String): Boolean {
    val dp = Array(s.length + 1) { BooleanArray(p.length + 1) }
    dp[0][0] = true
    for (j in 1 until dp[0].size) {
        if (p[j - 1] == '*') {
            dp[0][j] = dp[0][j - 2]
        }
    }
    for (i in 1..s.length) {
        for (j in 1..p.length) {
            if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else if (p[j - 1] == '*') {
                if (j > 1 && (p[j - 2] == s[i - 1] || p[j - 2] == '.')) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 2]
                } else {
                    dp[i][j] = dp[i][j - 2]
                }
            }
        }
    }
    return dp[s.length][p.length]
}