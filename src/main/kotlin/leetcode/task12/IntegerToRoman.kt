package org.example.leetcode.task12

// Integer to Roman
// LeetCode Task 12
// Level: Medium
// Data: 12/21/2024
// Time: 60 minutes
fun main() {
    println(intToRoman(3749))
}

fun intToRoman(num: Int): String {
    if (num > 3999 || num < 1) return ""

    var result = ""
    var buff = num

    val romanValues = listOf(
        1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
        100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
        10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
    )

    for ((value, symbol) in romanValues) {
        while (buff >= value) {
            result += symbol
            buff -= value
        }
    }

    return result
}

