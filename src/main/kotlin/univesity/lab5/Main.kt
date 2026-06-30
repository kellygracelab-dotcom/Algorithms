package univesity.lab5

import kotlin.random.Random

class GraphMatrix(val n: Int) {
    val adj = Array(n) { IntArray(n) }

    fun fillRandom(seed: Long = 42L) {
        val rng = Random(seed)
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val v = rng.nextInt(2)
                adj[i][j] = v; adj[j][i] = v
            }
        }
    }

    fun printMatrix() {
        println("Adjacency matrix:")
        print("   ")
        for (j in 0 until n) print("%3d".format(j + 1))
        println()
        for (i in 0 until n) {
            print("%3d".format(i + 1))
            for (j in 0 until n) print("%3d".format(adj[i][j]))
            println()
        }
    }

    fun dfs(start: Int): List<Int> {
        val visited = BooleanArray(n)
        val order = mutableListOf<Int>()
        fun visit(v: Int) {
            visited[v] = true
            order.add(v + 1)
            for (u in 0 until n) if (adj[v][u] == 1 && !visited[u]) visit(u)
        }
        visit(start)
        return order
    }
}

fun main() {
    val n = 7
    val g = GraphMatrix(n)
    g.fillRandom()
    g.printMatrix()

    val source = 0
    println("\nDFS from node ${source + 1}:")
    val order = g.dfs(source)
    println("  Visit order: $order")
}
