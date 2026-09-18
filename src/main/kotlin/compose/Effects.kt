package compose

/**
 * A model of how Compose runs DisposableEffect(key) { ... onDispose { } }.
 *
 * - first call to recompose(key)             → start the effect
 * - recompose with the same key              → do nothing
 * - recompose with a new key                 → dispose the old one first, then start the new one
 * - leave() (the component left the screen)  → dispose the current one
 *
 * effect is what gets started; it returns onDispose — what to call on cleanup.
 */
class EffectSlot<K>(
    private val effect: (K) -> (() -> Unit),
) {
    private var currentKey: K? = null
    private var onDispose: (() -> Unit)? = null

    fun recompose(key: K) {
        if (key == currentKey && onDispose != null) return

        onDispose?.invoke()
        onDispose = effect(key)
        currentKey = key
    }

    fun leave() {
        onDispose?.invoke()
        onDispose = null
        currentKey = null
    }
}