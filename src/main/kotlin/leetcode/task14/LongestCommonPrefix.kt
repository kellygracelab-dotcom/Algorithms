package org.example.leetcode.task14

// Longest Common Prefix
// LeetCode Task 14
// Level: Easy
// Data: 1/5/2025
// Time: 15 minutes
fun main() {
    println(longestCommonPrefix(arrayOf("flower","flow","flight")))
    println(longestCommonPrefixBestSolution(arrayOf("flower","flow","flight")))
}

fun longestCommonPrefix(strs: Array<String>): String {
    val sortedStrings = strs.sortedBy { it.length }
    var poz = 0
    var result = ""
    for (i in sortedStrings[0]) {
        var index = 1
        while (index < sortedStrings.size) {
            if (i != sortedStrings[index][poz]) {
                return result
            } else {
                index++
            }
        }
        result += i
        poz++
    }

    return result
}

fun longestCommonPrefixBestSolution(strs: Array<String>): String {
    if (strs.isEmpty()) return ""

    var prefix = strs[0]

    for (i in 1 until strs.size) {
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) return ""
        }
    }

    return prefix
}