package org.example.leetcode.task20

fun main() {
    println(isValid("{[(])}"))
}

fun isValid(s: String): Boolean {
    if (s.length % 2 != 0) return false

    val pairs = mapOf('(' to ')', '{' to '}', '[' to ']')
    val stack = ArrayDeque<Char>()

    for (ch in s) {
        if (ch in pairs.keys) {
            stack.addLast(ch)
        } else {
            if (stack.isEmpty()) return false
            val open = stack.removeLast()
            if (pairs[open] != ch) return false
        }
    }

    return stack.isEmpty()
}
