package concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun threadNameOfDefault(): String = withContext(Dispatchers.Default) {
    Thread.currentThread().name
}

suspend fun threadNameOfIo(): String = withContext(Dispatchers.IO) {
    Thread.currentThread().name
}

suspend fun suspendingWorkTakes(workers: Int, millis: Long): Long = coroutineScope {
    val start = System.currentTimeMillis()
    val jobs = List(workers) {
        launch { delay(millis) }
    }
    jobs.forEach { it.join() }
    System.currentTimeMillis() - start
}

suspend fun blockingWorkTakes(workers: Int, millis: Long): Long = coroutineScope {
    val start = System.currentTimeMillis()
    val jobs = List(workers) {
        launch(Dispatchers.Default) { Thread.sleep(millis) }
    }
    jobs.forEach { it.join() }
    System.currentTimeMillis() - start
}
