package katas

fun main() {
    report("wordToLength") { wordToLength(listOf("a", "bb", "ccc")) }
    report("countByFirstLetter") { countByFirstLetter(listOf("apple", "avocado", "banana")) }
    report("evensAndOdds") { evensAndOdds(listOf(1, 2, 3, 4)) }
    report("totalLength") { totalLength(listOf("ab", "cde")) }
    report("fullNames") { fullNames(listOf("John", "Jane"), listOf("Doe", "Roe")) }
    report("longestWord") { longestWord(listOf("a", "ccc", "bb")) }
    report("uniqueChars") { uniqueChars(listOf("abc", "bcd")) }
    report("byLengthThenAlphabetical") { byLengthThenAlphabetical(listOf("bb", "a", "cc", "b")) }
}

private fun report(name: String, block: () -> Any?) {
    val result = runCatching(block)
    if (result.isSuccess) {
        println("$name = ${result.getOrNull()}")
    } else {
        println("$name = NOT DONE (${result.exceptionOrNull()?.message})")
    }
}
