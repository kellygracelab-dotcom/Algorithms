package katas

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Collections2Test {

    @Test
    fun `wordToLength maps each word to its length`() {
        assertEquals(mapOf("a" to 1, "bb" to 2, "ccc" to 3), wordToLength(listOf("a", "bb", "ccc")))
    }

    @Test
    fun `countByFirstLetter counts how many words start with each letter`() {
        assertEquals(mapOf('a' to 2, 'b' to 1), countByFirstLetter(listOf("apple", "avocado", "banana")))
    }

    @Test
    fun `evensAndOdds splits numbers into evens and odds`() {
        assertEquals(listOf(2, 4) to listOf(1, 3), evensAndOdds(listOf(1, 2, 3, 4)))
    }

    @Test
    fun `totalLength sums the lengths of all words`() {
        assertEquals(5, totalLength(listOf("ab", "cde")))
    }

    @Test
    fun `fullNames combines first and last names positionally`() {
        assertEquals(
            listOf("John Doe", "Jane Roe"),
            fullNames(listOf("John", "Jane"), listOf("Doe", "Roe")),
        )
    }

    @Test
    fun `longestWord returns the longest word or null when empty`() {
        assertEquals("ccc", longestWord(listOf("a", "ccc", "bb")))
        assertNull(longestWord(emptyList()))
    }

    @Test
    fun `uniqueChars returns distinct characters across all words in order`() {
        assertEquals(listOf('a', 'b', 'c', 'd'), uniqueChars(listOf("abc", "bcd")))
    }

    @Test
    fun `byLengthThenAlphabetical sorts by length then alphabetically`() {
        assertEquals(
            listOf("a", "b", "bb", "cc"),
            byLengthThenAlphabetical(listOf("bb", "a", "cc", "b")),
        )
    }
}
