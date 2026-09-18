package nullsafety

/**
 * token is var, not val: a session can be logged out or reissued on the fly.
 * This is exactly the scenario the Kotlin compiler refuses to smart-cast a
 * var class property for — another thread could have changed it.
 */
class Session(var token: String?, val userId: String)

/**
 * A greeting for the screen. Must never crash — a guest still sees something.
 *
 * TODO: no `!!`. Copy session.token into a local val, then safe call and elvis.
 *  No token -> "Hello, guest".
 *  Has a token -> "Hello, ${userId} (••••${last 4 characters of the token})".
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
 * For operations that require a token (a request to the server, say).
 *
 * TODO: no token -> throw IllegalStateException("Session $userId has no token").
 *  Has a token -> return it. session.token is var, so `if (session.token != null)`
 *  won't smart-cast; save it into a local val first.
 */
fun requireToken(session: Session): String {
    val local = session.token
    if (local.isNullOrEmpty()) {
        throw IllegalStateException("Session ${session.userId} has no token")
    } else {
        return local
    }
}
