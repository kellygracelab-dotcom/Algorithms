package nullsafety

/**
 * token — var, не val: сессию можно разлогинить или перевыпустить на лету.
 * Это ровно тот сценарий из-за которого компилятор Kotlin отказывается
 * делать smart cast на var-свойстве класса — другой поток мог его поменять.
 */
class Session(var token: String?, val userId: String)

/**
 * Приветствие для экрана. Никогда не должно падать — гостю показываем то, что есть.
 *
 * TODO: без `!!`. Скопируй session.token в локальную val, дальше safe call и элвис.
 *  Нет токена -> "Hello, guest".
 *  Есть токен -> "Hello, ${userId} (••••${последние 4 символа токена})".
 */
fun greeting(session: Session): String {
    val local = session.token
    return if (local.isNullOrEmpty()) {
        "Hello, guest"
    } else {
        "Hello, ${session.userId} (••••${local.takeLast(4)})"
    }
}

/**
 * Для операций, которым токен обязателен (например, запрос к серверу).
 *
 * TODO: нет токена -> брось IllegalStateException("Session $userId has no token").
 *  Есть токен -> верни его. session.token — var, поэтому `if (session.token != null)`
 *  не даст smart cast; сначала сохрани в локальную val.
 */
fun requireToken(session: Session): String {
    val local = session.token
    if (local.isNullOrEmpty()) {
        throw IllegalStateException("Session ${session.userId} has no token")
    } else {
        return local
    }
}
