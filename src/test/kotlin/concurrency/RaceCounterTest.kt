package concurrency

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val WORKERS = 8
private const val PER_WORKER = 20_000
private const val EXPECTED = WORKERS * PER_WORKER

class RaceCounterTest {

    @Test
    fun `naive counter loses increments because reading and writing are two steps`() = runTest {
        val counter = NaiveCounter()

        countInParallel(WORKERS, PER_WORKER) { counter.increment() }

        println("naive counter: ${counter.value} of $EXPECTED")
        assertTrue(counter.value <= EXPECTED)
    }

    @Test
    fun `atomic counter keeps every increment`() = runTest {
        val counter = AtomicCounter()

        countInParallel(WORKERS, PER_WORKER) { counter.increment() }

        assertEquals(EXPECTED, counter.value)
    }

    @Test
    fun `mutex counter keeps every increment`() = runTest {
        val counter = MutexCounter()

        countInParallel(WORKERS, PER_WORKER) { counter.increment() }

        assertEquals(EXPECTED, counter.value)
    }
}
