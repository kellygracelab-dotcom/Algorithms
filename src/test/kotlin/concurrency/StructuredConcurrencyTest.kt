package concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StructuredConcurrencyTest {

    @Test
    fun `a scope waits for every child before it returns`() = runTest {
        val log = mutableListOf<String>()

        bothChildrenFinish(log)

        assertEquals(listOf("first", "second"), log)
    }

    @Test
    fun `one failing child cancels its siblings`() = runTest {
        val log = mutableListOf<String>()

        val result = siblingDiesWithFailure(log)

        assertTrue(result.isFailure)
        assertTrue(log.isEmpty())
    }

    @Test
    fun `a supervisor scope lets siblings survive a failure`() = runTest {
        val log = mutableListOf<String>()

        siblingSurvivesFailure(log)

        assertEquals(listOf("child failed with boom", "slow sibling finished"), log)
    }

    @Test
    fun `async runs the two calls at the same time`() = runBlocking {
        val sequential = sequentialWork()
        val parallel = parallelWork()

        println("sequential ${sequential}ms, parallel ${parallel}ms")
        assertTrue(parallel < sequential)
    }

    @Test
    fun `work started in a cancelled scope never runs`() = runTest {
        val log = mutableListOf<String>()
        val scope = CoroutineScope(Job())

        scopeIsNotAContainer(scope, log)
        scope.cancel()
        delay(100)

        assertTrue(log.isEmpty())
    }
}
