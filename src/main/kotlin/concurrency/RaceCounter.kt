package concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

class NaiveCounter {
    var value = 0
        private set

    fun increment() {
        value = value + 1
    }
}

class AtomicCounter {
    private val counter = AtomicInteger(0)
    val value: Int get() = counter.get()

    fun increment() {
        counter.incrementAndGet()
    }
}

class MutexCounter {
    private val mutex = Mutex()
    var value = 0
        private set

    suspend fun increment() = mutex.withLock {
        value = value + 1
    }
}

suspend fun countInParallel(workers: Int, perWorker: Int, increment: suspend () -> Unit) {
    coroutineScope {
        repeat(workers) {
            launch(Dispatchers.Default) {
                repeat(perWorker) { increment() }
            }
        }
    }
}
