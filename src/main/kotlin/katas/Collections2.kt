package katas

fun wordToLength(words: List<String>): Map<String, Int> {
    return words.associateWith { word -> word.length }
}

fun countByFirstLetter(words: List<String>): Map<Char, Int> {
    return words.groupBy { word -> word.first() }.mapValues { it.value.size }
}

fun evensAndOdds(numbers: List<Int>): Pair<List<Int>, List<Int>> {
    return numbers.partition { number -> number % 2 == 0 }
}

fun totalLength(words: List<String>): Int {
    return words.sumOf { word -> word.length }
}

fun fullNames(firstNames: List<String>, lastNames: List<String>): List<String> {
    return firstNames.zip(lastNames).toList().map { pair -> pair.first + " " + pair.second }
}

fun longestWord(words: List<String>): String? {
    return words.maxByOrNull { word -> word.length }
}

fun uniqueChars(words: List<String>): List<Char> {
    return words.flatMap { it.toList() }.distinct()
}

fun byLengthThenAlphabetical(words: List<String>): List<String> {
    return words.sortedWith(compareBy({ it.length }, { it }))
}
