package generics

import kotlin.test.Test
import kotlin.test.assertEquals

class KeepOnlyTest {

    private val mixed = listOf("a", 1, "b", 2.0, "c", 3)

    @Test
    fun `keeps only strings from a mixed list`() {
        val strings: List<String> = mixed.keepOnly()
        assertEquals(listOf("a", "b", "c"), strings)
    }

    @Test
    fun `keeps only ints from a mixed list`() {
        val ints: List<Int> = mixed.keepOnly()
        assertEquals(listOf(1, 3), ints)
    }
}
