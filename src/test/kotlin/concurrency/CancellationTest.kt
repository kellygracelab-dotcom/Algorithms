package concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CancellationTest {

    @Test
    fun `cleanup still runs after cancellation when it is marked non cancellable`() = runBlocking {
        val log = mutableListOf<String>()
        val job = launch { cleansUpOnCancellation(log) }

        delay(50)
        job.cancelAndJoin()

        assertEquals(listOf("cleaned up"), log)
    }

    @Test
    fun `catching every exception swallows cancellation and hides it`() = runBlocking {
        val log = mutableListOf<String>()
        val job = launch { swallowsCancellation(log) }

        delay(50)
        job.cancelAndJoin()

        assertEquals(listOf("swallowed JobCancellationException"), log)
    }

    @Test
    fun `rethrowing cancellation keeps the coroutine honest`() = runBlocking {
        val log = mutableListOf<String>()
        val job = launch { rethrowsCancellation(log) }

        delay(50)
        job.cancelAndJoin()

        assertEquals(listOf("cancelled"), log)
    }

    @Test
    fun `a busy loop without a check cannot be cancelled once it has started`() = runBlocking {
        val scope = CoroutineScope(Job() + Dispatchers.Default)
        val started = AtomicBoolean(false)
        var completed = false

        val job = scope.launch {
            started.set(true)
            busyLoopIgnoresCancellation(200_000_000)
            completed = true
        }
        while (!started.get()) delay(1)
        job.cancel()
        job.join()

        assertTrue(completed)
    }

    @Test
    fun `a busy loop that checks stops when cancelled`() = runBlocking {
        val scope = CoroutineScope(Job() + Dispatchers.Default)
        val started = AtomicBoolean(false)
        var completed = false

        val job = scope.launch {
            started.set(true)
            busyLoopChecksCancellation(200_000_000)
            completed = true
        }
        while (!started.get()) delay(1)
        job.cancel()
        job.join()

        assertTrue(!completed)
    }

    @Test
    fun `withTimeoutOrNull gives up instead of throwing`() = runBlocking {
        val result = withTimeoutOrNull(30) {
            delay(1_000)
            "done"
        }

        assertEquals(null, result)
    }
}

private suspend fun kotlinx.coroutines.Job.cancelAndJoin() {
    cancel()
    join()
}
