package univesity.lab4

data class Edge(val u: Int, val v: Int, val weight: Int)

class GraphWeighted(val n: Int, private val edges: List<Edge>) {

    fun printGraph() {
        val adj = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        for (i in 0 until n) adj[i][i] = 0
        for (e in edges) { adj[e.u][e.v] = e.weight; adj[e.v][e.u] = e.weight }
        println("Adjacency matrix (INF = no edge):")
        print("     ")
        for (j in 0 until n) print("%4d ".format(j + 1))
        println()
        for (i in 0 until n) {
            print("%4d ".format(i + 1))
            for (j in 0 until n) {
                if (adj[i][j] == Int.MAX_VALUE) print(" INF ") else print("%4d ".format(adj[i][j]))
            }
            println()
        }
        println("Edges: ${edges.map { "(${it.u+1}-${it.v+1}, w=${it.weight})" }}")
    }

    // Union-Find
    private fun find(parent: IntArray, i: Int): Int {
        if (parent[i] != i) parent[i] = find(parent, parent[i])
        return parent[i]
    }

    private fun union(parent: IntArray, rank: IntArray, a: Int, b: Int) {
        val ra = find(parent, a); val rb = find(parent, b)
        when {
            rank[ra] < rank[rb] -> parent[ra] = rb
            rank[ra] > rank[rb] -> parent[rb] = ra
            else -> { parent[rb] = ra; rank[ra]++ }
        }
    }

    fun kruskal(): List<Edge> {
        val sorted = edges.sortedBy { it.weight }
        val parent = IntArray(n) { it }
        val rank = IntArray(n)
        val mst = mutableListOf<Edge>()
        for (e in sorted) {
            val pu = find(parent, e.u); val pv = find(parent, e.v)
            if (pu != pv) { union(parent, rank, e.u, e.v); mst.add(e) }
            if (mst.size == n - 1) break
        }
        return mst
    }

    fun dijkstra(src: Int): Pair<IntArray, IntArray> {
        val dist = IntArray(n) { Int.MAX_VALUE }
        val prev = IntArray(n) { -1 }
        val visited = BooleanArray(n)
        dist[src] = 0

        // adjacency list built from edges
        val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for (e in edges) { adj[e.u].add(e.v to e.weight); adj[e.v].add(e.u to e.weight) }

        repeat(n) {
            var u = -1
            for (i in 0 until n) if (!visited[i] && (u == -1 || dist[i] < dist[u])) u = i
            if (u == -1 || dist[u] == Int.MAX_VALUE) return@repeat
            visited[u] = true
            for ((v, w) in adj[u]) {
                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w; prev[v] = u
                }
            }
        }
        return dist to prev
    }
}
