package compose

import kotlin.test.Test
import kotlin.test.assertEquals

class EffectsTest {

    @Test
    fun `same key does not restart the effect`() {
        val log = mutableListOf<String>()
        val slot = EffectSlot<String> { key ->
            log += "start $key"
            val onDispose = { log += "dispose $key" }
            onDispose
        }

        slot.recompose("anya")
        slot.recompose("anya")
        slot.recompose("anya")

        assertEquals(listOf("start anya"), log)
    }

    @Test
    fun `new key disposes the old effect before starting the new one`() {
        val events = mutableListOf<String>()

        val slot = EffectSlot<String> { key ->
            events += "start $key"

            val dispose: () -> Unit = {
                events += "dispose $key"
            }
            dispose
        }

        slot.recompose("anya")
        slot.recompose("borya")

        assertEquals(
            listOf(
                "start anya",
                "dispose anya",
                "start borya",
            ),
            events,
        )
    }

    @Test
    fun `leaving the screen disposes the current effect`() {
        val events = mutableListOf<String>()

        val slot = EffectSlot<String> { key ->
            events += "start $key"

            val dispose: () -> Unit = {
                events += "dispose $key"
            }
            dispose
        }

        slot.recompose("anya")
        slot.leave()

        assertEquals(
            listOf(
                "start anya",
                "dispose anya",
            ),
            events,
        )
    }
}
