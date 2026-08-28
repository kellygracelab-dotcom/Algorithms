package concurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

suspend fun bothChildrenFinish(log: MutableList<String>) = coroutineScope {
    launch {
        delay(10)
        log += "first"
    }
    launch {
        delay(20)
        log += "second"
    }
}

suspend fun siblingDiesWithFailure(log: MutableList<String>) = runCatching {
    coroutineScope {
        launch {
            delay(50)
            log += "slow sibling finished"
        }
        launch {
            delay(10)
            throw IllegalStateException("boom")
        }
    }
}

suspend fun siblingSurvivesFailure(log: MutableList<String>) = supervisorScope {
    val reportFailure = CoroutineExceptionHandler { _, error ->
        log += "child failed with ${error.message}"
    }
    launch {
        delay(50)
        log += "slow sibling finished"
    }
    launch(reportFailure) {
        delay(10)
        throw IllegalStateException("boom")
    }
    delay(100)
}

suspend fun sequentialWork(): Long = coroutineScope {
    val start = System.currentTimeMillis()
    slowValue(50)
    slowValue(50)
    System.currentTimeMillis() - start
}

suspend fun parallelWork(): Long = coroutineScope {
    val start = System.currentTimeMillis()
    val first = async { slowValue(50) }
    val second = async { slowValue(50) }
    first.await()
    second.await()
    System.currentTimeMillis() - start
}

private suspend fun slowValue(millis: Long): Int {
    delay(millis)
    return 1
}

fun scopeIsNotAContainer(scope: CoroutineScope, log: MutableList<String>) {
    scope.launch {
        delay(30)
        log += "never runs if the scope is cancelled first"
    }
}
