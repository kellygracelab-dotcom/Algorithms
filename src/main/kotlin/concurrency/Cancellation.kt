package concurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

suspend fun cleansUpOnCancellation(log: MutableList<String>) {
    try {
        delay(1_000)
        log += "finished"
    } finally {
        withContext(NonCancellable) {
            delay(10)
            log += "cleaned up"
        }
    }
}

suspend fun swallowsCancellation(log: MutableList<String>) {
    try {
        delay(1_000)
        log += "finished"
    } catch (e: Exception) {
        log += "swallowed ${e::class.simpleName}"
    }
}

suspend fun rethrowsCancellation(log: MutableList<String>) {
    try {
        delay(1_000)
        log += "finished"
    } catch (e: CancellationException) {
        log += "cancelled"
        throw e
    } catch (e: Exception) {
        log += "real failure"
    }
}

suspend fun busyLoopIgnoresCancellation(iterations: Int): Int {
    var seen = 0
    repeat(iterations) { seen++ }
    return seen
}

suspend fun busyLoopChecksCancellation(iterations: Int): Int {
    var seen = 0
    repeat(iterations) {
        coroutineContext.ensureActive()
        seen++
    }
    return seen
}
