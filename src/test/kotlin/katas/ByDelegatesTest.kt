package katas

import kotlin.test.Test
import kotlin.test.assertEquals

class ByDelegatesTest {

    @Test
    fun `UppercaseDelegate returns the value in uppercase`() {
        assertEquals("HELLO", Greeting().message)
    }

    @Test
    fun `RangeDelegate clamps values into the given range`() {
        val volume = Volume()
        assertEquals(50, volume.level)

        volume.level = 42
        assertEquals(42, volume.level)

        volume.level = 150
        assertEquals(100, volume.level)

        volume.level = -10
        assertEquals(0, volume.level)
    }

    @Test
    fun `lazy value is computed only once`() {
        val holder = LazyCounterHolder()
        assertEquals(0, holder.computeCount)

        holder.value
        holder.value
        holder.value

        assertEquals(1, holder.computeCount)
    }

    @Test
    fun `observable delegate records old and new values on every change`() {
        val holder = ObservableNameHolder()

        holder.name = "a"
        holder.name = "b"

        assertEquals(listOf("initial" to "a", "a" to "b"), holder.changes)
    }

    @Test
    fun `class delegation implements the interface without overriding anything`() {
        val greeter = QuietGreeter(SimpleGreeter("Danylo"))
        assertEquals("Hello, Danylo", greeter.greet())
    }

    @Test
    fun `class delegation still allows overriding a single method`() {
        val greeter = LoudGreeter(SimpleGreeter("Danylo"))
        assertEquals("HELLO, DANYLO", greeter.greet())
    }
}
