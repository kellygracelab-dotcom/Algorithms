package org.example.leetcode.task17

// Letter Combinations of a Phone Number
// LeetCode Task 17
// Level: Medium
// Data: 3/28/2025
// Time: 25 minutes
fun main() {
    val digits = "23"
    val result = letterCombinations(digits)

    println(result)
}

fun letterCombinations(digits: String): List<String> {
    if (digits == "") {
        return emptyList()
    }

    val digitToChars = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    val charsList = mutableListOf<String>()

    for (digit in digits) {
        val letters = digitToChars[digit]
        if (letters != null) {
            charsList.add(letters)
        }
    }

    var result = listOf("")

    for (letters in charsList) {
        val newResult = mutableListOf<String>()

        for (prefix in result) {
            for (letter in letters) {
                newResult.add(prefix + letter)
            }
        }

        result = newResult
    }

    return result
}
