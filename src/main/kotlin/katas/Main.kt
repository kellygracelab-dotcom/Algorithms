package katas

fun main() {
    runKata("doubled") { doubled(listOf(1, 2, 3)) }
    runKata("evens") { evens(listOf(1, 2, 3, 4, 5)) }
    runKata("withoutBlanks") { withoutBlanks(listOf("a", "", "  ", "b")) }
    runKata("allChars") { allChars(listOf("ab", "cd")) }
    runKata("hasNegative") { hasNegative(listOf(1, -2, 3)) }
    runKata("byFirstLetter") { byFirstLetter(listOf("apple", "avocado", "banana")) }
    runKata("sumWithFold") { sumWithFold(listOf(1, 2, 3, 4)) }
    runKata("longestFirst") { longestFirst(listOf("apple", "kiwi", "banana")) }
}

private fun runKata(name: String, block: () -> Any?) {
    val result = runCatching(block)
    if (result.isSuccess) {
        println("$name = ${result.getOrNull()}")
    } else {
        println("$name = NOT DONE (${result.exceptionOrNull()?.message})")
    }
}
