package katas

fun doubled(numbers: List<Int>): List<Int> {
    return numbers.map { number -> number * 2 }
}

fun evens(numbers: List<Int>): List<Int> {
    return numbers.filter { number -> number % 2 == 0 }
}

fun withoutBlanks(words: List<String>): List<String> {
    return words.filter { word -> word.isNotBlank() }
}

fun allChars(words: List<String>): List<Char> {
    return words.flatMap { word -> word.toCharArray().toList() }
}


fun hasNegative(numbers: List<Int>): Boolean {
    return numbers.any { number -> number < 0 }
}


fun byFirstLetter(words: List<String>): Map<Char, List<String>> {
    return words.groupBy { word -> word.first() }
}

fun sumWithFold(numbers: List<Int>): Int {
    return numbers.fold(0) { a, n -> a + n }
}

fun longestFirst(words: List<String>): List<String> {
    return words.sortedByDescending { word -> word.length }
}
