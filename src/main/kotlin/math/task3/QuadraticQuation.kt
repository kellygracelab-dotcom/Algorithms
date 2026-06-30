package org.example.math.task3

import kotlin.math.sqrt

fun main() {
    val (x1, x2) = solveQuadraticEquation(2.0, 4.0, -6.0)
    if (x1 != null && x2 != null) {
        println("Корни уравнения: x1 = $x1, x2 = $x2")
    } else {
        println("Уравнение не имеет действительных корней.")
    }
}

fun solveQuadraticEquation(a: Double, b: Double, c: Double): Pair<Double?, Double?> {
    val discriminant = b * b - 4 * a * c

    return when {
        discriminant > 0 -> {
            val x1 = (-b + sqrt(discriminant)) / (2 * a)
            val x2 = (-b - sqrt(discriminant)) / (2 * a)
            Pair(x1, x2)
        }
        discriminant == 0.0 -> {
            val x = -b / (2 * a)
            Pair(x, x)
        }
        else -> Pair(null, null)
    }
}