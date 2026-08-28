package concurrency

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ColdAndHotTest {

    @Test
    fun `a cold flow starts again for every collector`() = runTest {
        val source = ColdSource()

        source.numbers().toList()
        source.numbers().toList()

        assertEquals(2, source.startCount)
    }

    @Test
    fun `a state flow always has a current value`() = runTest {
        val source = HotSource()

        source.set(7)

        assertEquals(7, source.state.value)
        assertEquals(7, source.state.first())
    }

    @Test
    fun `a state flow drops values nobody was there to see`() = runTest {
        val source = HotSource()

        source.set(1)
        source.set(2)
        source.set(3)

        assertEquals(3, source.state.first())
    }
}
