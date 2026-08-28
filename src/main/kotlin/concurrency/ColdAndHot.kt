package concurrency

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class ColdSource {
    var startCount = 0
        private set

    fun numbers(): Flow<Int> = flow {
        startCount++
        emit(1)
        emit(2)
        emit(3)
    }
}

class HotSource {
    private val _state = MutableStateFlow(0)
    val state: StateFlow<Int> = _state.asStateFlow()

    private val _events = MutableSharedFlow<String>()
    val events: SharedFlow<String> = _events.asSharedFlow()

    fun set(value: Int) {
        _state.value = value
    }

    suspend fun emitEvent(event: String) {
        _events.emit(event)
    }
}
