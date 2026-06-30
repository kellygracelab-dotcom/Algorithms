package katas

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CollectionsTest {

    @Test
    fun `doubled multiplies each element by 2`() {
        assertEquals(listOf(2, 4, 6), doubled(listOf(1, 2, 3)))
    }

    @Test
    fun `evens keeps only even numbers`() {
        assertEquals(listOf(2, 4), evens(listOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `withoutBlanks removes empty and whitespace strings`() {
        assertEquals(listOf("a", "b"), withoutBlanks(listOf("a", "", "  ", "b")))
    }

    @Test
    fun `allChars flattens characters of all words into one list`() {
        assertEquals(listOf('a', 'b', 'c', 'd'), allChars(listOf("ab", "cd")))
    }

    @Test
    fun `hasNegative is true when at least one number is negative`() {
        assertTrue(hasNegative(listOf(1, -2, 3)))
        assertFalse(hasNegative(listOf(1, 2, 3)))
    }

    @Test
    fun `byFirstLetter groups words by their first character`() {
        val result = byFirstLetter(listOf("apple", "avocado", "banana"))
        assertEquals(listOf("apple", "avocado"), result['a'])
        assertEquals(listOf("banana"), result['b'])
    }

    @Test
    fun `sumWithFold sums all numbers`() {
        assertEquals(10, sumWithFold(listOf(1, 2, 3, 4)))
    }

    @Test
    fun `longestFirst sorts words by length descending`() {
        assertEquals(listOf("banana", "apple", "kiwi"), longestFirst(listOf("apple", "kiwi", "banana")))
    }
}
