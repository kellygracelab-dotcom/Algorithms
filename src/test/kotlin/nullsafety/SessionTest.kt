package nullsafety

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SessionTest {

    @Test
    fun `greeting falls back to guest when token is null`() {
        val session = Session(token = null, userId = "u1")
        assertEquals("Hello, guest", greeting(session))
    }

    @Test
    fun `greeting shows userId and last four chars of the token`() {
        // TODO: a session with userId "u1" and token "abc123456" — what string should come back?
        val session = Session("abc123456", "u1")

        assertEquals("Hello, u1 (••••3456)", greeting(session))
    }

    @Test
    fun `requireToken throws when token is null`() {
        val session = Session(token = null, userId = "u42")
        val error = assertFailsWith<IllegalStateException> { requireToken(session) }
        assertEquals("Session u42 has no token", error.message)
    }

    @Test
    fun `requireToken returns the token when present`() {
        val session = Session(token = "token", userId = "u42")
        assertEquals("token", requireToken(session))
    }
}
