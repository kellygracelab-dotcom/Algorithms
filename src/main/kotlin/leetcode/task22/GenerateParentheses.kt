package org.example.leetcode.task22

fun main() {
    println(generateParenthesis(3))
}

fun generateParenthesis(n: Int): List<String> {
    val result = mutableListOf<String>()

    fun backtrack(current: String, opened: Int, closed: Int) {
        if (opened == n && closed == n) {
            result.add(current)
            return
        }
        if (opened < n) {
            backtrack(current + "(", opened + 1, closed)
        }
        if (closed < opened) {
            backtrack(current + ")", opened, closed + 1)
        }
    }

    backtrack("(", 1, 0)
    return result
}