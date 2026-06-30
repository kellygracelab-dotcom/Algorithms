package org.example.math.task2

fun main() {
    println(getLineResult(3, -5, 14))
}

fun getLineResult(a: Int, b: Int, c: Int): Int {
    return (c - b) / a
}