package univesity.lab4

fun main() {
    val edges = listOf(
        Edge(0, 1, 10),
        Edge(0, 2, 200),
        Edge(1, 2, 50),
        Edge(1, 3, 8),
        Edge(2, 4, 3),
        Edge(3, 2, 100),
        Edge(3, 4, 16),
        Edge(4, 2, 6)
    )
    val g = GraphWeighted(5, edges)

    println("=== Graph ===")
    g.printGraph()

    println("\n=== Kruskal MST ===")
    val mst = g.kruskal()
    var totalWeight = 0
    for (e in mst) {
        println("  Edge ${e.u+1} -- ${e.v+1}  weight = ${e.weight}")
        totalWeight += e.weight
    }
    println("  Total MST weight: $totalWeight")

    println("\n=== Dijkstra (source = node 1) ===")
    val src = 0
    val (dist, prev) = g.dijkstra(src)
    println("  Distances from node ${src+1}:")
    for (i in dist.indices) {
        val d = if (dist[i] == Int.MAX_VALUE) "INF" else dist[i].toString()
        println("    -> node ${i+1}: $d")
    }
    println("\n  Shortest path tree (prev node):")
    for (i in prev.indices) {
        if (i == src) println("    node ${i+1}: source")
        else if (prev[i] == -1) println("    node ${i+1}: unreachable")
        else println("    node ${i+1}: via ${prev[i]+1}")
    }

    println("\n=== Dijkstra routing table ===")
    println("  Dst | Dist | Next hop")
    for (i in dist.indices) {
        if (i == src) { println("  %3d |  src |    -".format(i+1)); continue }
        val d = if (dist[i] == Int.MAX_VALUE) " INF" else "%4d".format(dist[i])
        val hop = if (prev[i] == -1) " -" else "%2d".format(prev[i]+1)
        println("  %3d |%s  |   %s".format(i+1, d, hop))
    }
}
