package compose

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StabilityTest {

    @Test
    fun `equal ints are stable and skippable`() {
        assertTrue(shouldSkip(42, 42))
    }

    @Test
    fun `different ints are not skippable`() {
        assertFalse(shouldSkip(5, 6))
    }

    @Test
    fun `equal lists are not skippable because List is unstable`() {
        val a = listOf(1, 2, 3)
        val b = listOf(1, 2, 3)

        assertFalse(shouldSkip(a, b))
    }

    @Test
    fun `equal immutable wrappers are skippable`() {
        val a = Immutable(listOf(1, 2, 3))
        val b = Immutable(listOf(1, 2, 3))

        assertTrue(shouldSkip(a, b))
    }
}
