package org.example.leetcode.task13

// Roman to Integer
// LeetCode Task 13
// Level: Easy
// Data: 12/28/2024
// Time: 10 minutes
fun main() {
    println(romanToInt("MCMXCIV"))
}

fun romanToInt(s: String): Int {

    val romanValues = mapOf(
        "M" to 1000, "CM" to 900, "D" to 500, "CD" to 400,
        "C" to 100, "XC" to 90, "L" to 50, "XL" to 40,
        "X" to 10, "IX" to 9, "V" to 5, "IV" to 4, "I" to 1
    )

    var result = 0
    var index = 0

    while (index < s.length) {
        if (index == s.length - 1) {
            result += romanValues[s[index].toString()]!!
        } else {
            val buff = s[index].toString() + s[index + 1].toString()
            if (romanValues.containsKey(buff)) {
                result += romanValues[buff]!!
                index++
            } else {
                result += romanValues[s[index].toString()]!!
            }
        }

        index++
    }

    return result
}

