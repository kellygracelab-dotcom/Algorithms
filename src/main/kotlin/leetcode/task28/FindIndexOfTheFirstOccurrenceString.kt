package org.example.leetcode.task28

fun main() {
    println(strStr("mississippi", "issip"))
}

fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    if (needle.length > haystack.length) return -1

    val lastStart = haystack.length - needle.length

    for (i in 0..lastStart) {
        if (haystack[i] == needle[0]) {
            if (haystack.regionMatches(i, needle, 0, needle.length)) {
                return i
            }
        }
    }
    return -1
}
