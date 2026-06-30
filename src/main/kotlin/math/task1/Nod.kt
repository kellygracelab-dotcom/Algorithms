package org.example.math.task1

fun main() {
    println(getNod(48, 18))
    println(getNok(48, 18))
}

fun getNod(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b

    while (num2 != 0) {
        val temp = num1 % num2
        num1 = num2
        num2 = temp
    }

    return num1
}

fun getNok(a: Int, b: Int): Int {
    return a * b / getNod(a, b)
}