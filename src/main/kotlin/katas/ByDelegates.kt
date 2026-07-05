package katas

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class UppercaseDelegate(private val value: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value.uppercase()
    }
}

class Greeting {
    val message: String by UppercaseDelegate("hello")
}

class RangeDelegate(startValue: Int, private val range: IntRange) {
    private var value: Int = startValue

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
        value = if (newValue < range.first) {
            range.first
        } else if (newValue > range.last) {
            range.last
        } else {
            newValue
        }
    }
}

class Volume {
    var level: Int by RangeDelegate(50, 0..100)
}

class LazyCounterHolder {
    var computeCount = 0

    val value: Int by lazy {
        computeCount++
    }
}

class ObservableNameHolder {
    val changes = mutableListOf<Pair<String, String>>()

    var name: String by Delegates.observable("initial") { _, old, new ->
        changes.add(Pair(old, new))
    }
}

interface Greeter {
    fun greet(): String
}

class SimpleGreeter(private val name: String) : Greeter {
    override fun greet() = "Hello, $name"
}

class QuietGreeter(base: Greeter) : Greeter by base

class LoudGreeter(private val base: Greeter) : Greeter by base {
    override fun greet(): String {
        return base.greet().uppercase()
    }
}
