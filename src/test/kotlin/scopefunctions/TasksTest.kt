package scopefunctions

import kotlin.test.Test
import kotlin.test.assertEquals

class TasksTest {

    @Test
    fun `greet returns guest fallback for null`() {
        assertEquals("Hello, guest", greet(null))
    }

    @Test
    fun `greet returns username for non-null`() {
        assertEquals("Hello, Danylo", greet("Danylo"))
    }

    @Test
    fun `buildProfile returns a fully configured profile`() {
        val profile = buildProfile("Danylo", 25)
        assertEquals("Danylo", profile.name)
        assertEquals(25, profile.age)
        assertEquals("New user", profile.bio)
    }

    @Test
    fun `addAndCount adds the value and returns the new size`() {
        val list = mutableListOf(1, 2, 3)
        val size = addAndCount(list, 4)
        assertEquals(4, size)
        assertEquals(listOf(1, 2, 3, 4), list)
    }

    @Test
    fun `summarize describes the profile in one line`() {
        val profile = Profile("Danylo", 25, "New user")
        assertEquals("Danylo, 25 y.o. — New user", summarize(profile))
    }
}
