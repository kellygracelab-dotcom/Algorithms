package org.example.leetcode.task6


// Zigzag Conversion
// LeetCode Task 6
// Level: Medium
// Data: 08/11/2024 - 10/11/2024
// Time: 12= hours 13 minutes
fun main() {
    println(convert("PAYPALISHIRING", 4))
}

fun convert(s: String, numRows: Int): String {
    if (numRows == 1 || s.length <= numRows) return s
    val rows = Array(numRows) { StringBuilder() }
    var currentRow = 0
    var goingDown = false

     for (char in s) {
        rows[currentRow].append(char)
        if (currentRow == 0 || currentRow == numRows - 1) goingDown = !goingDown
        currentRow += if (goingDown) 1 else -1
    }
    return rows.joinToString("") { it.toString() }
}
