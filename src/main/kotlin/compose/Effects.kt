package compose

/**
 * Модель того, как Compose ведёт DisposableEffect(key) { ... onDispose { } }.
 *
 * - первый вызов recompose(key)              → запустить эффект
 * - recompose с тем же ключом                → ничего не делать
 * - recompose с новым ключом                 → сначала убрать старый, потом запустить новый
 * - leave() (компонент ушёл с экрана)        → убрать текущий
 *
 * effect — то, что запускается; он возвращает onDispose — то, что вызвать при уборке.
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