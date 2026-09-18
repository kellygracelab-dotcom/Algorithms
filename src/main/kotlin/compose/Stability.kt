package compose

/**
 * A model of how Compose decides whether a composable's recomposition can be skipped.
 *
 * Compose's rule: skipping is allowed only if the parameter is STABLE and the value
 * hasn't changed. Stable means Compose can trust equals: the type is either immutable,
 * or mutable but reports its own changes (like MutableState).
 *
 * List<T> is unstable: a MutableList might be hiding behind the interface —
 * it can change, but it has no way to say so.
 */

/** A promise-wrapper: "this list won't change anymore". Analogous to ImmutableList. */
class Immutable<T>(private val items: List<T>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Immutable<*>) return false

        return this.items == other.items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }
}

/** true if the type is considered stable — meaning its equals can be trusted. */
private fun isStable(value: Any?): Boolean {
    return value == null || value is Int || value is String || value is Boolean || value is Immutable<*>
}
/**
 * Skipping is allowed only if both values are stable and equal.
 *
 * @return true — the parameter hasn't changed, no recomposition needed.
 */
fun <T> shouldSkip(old: T, new: T): Boolean {
    if (!isStable(old) || !isStable(new)) {
        return false
    }
    return old == new
}