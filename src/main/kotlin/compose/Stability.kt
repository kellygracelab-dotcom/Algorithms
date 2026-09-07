package compose

/**
 * Модель того, как Compose решает, можно ли пропустить перезапуск composable-функции.
 *
 * Правило Compose: пропустить можно, если параметр СТАБИЛЕН и значение не изменилось.
 * Стабилен — значит Compose может доверять equals: тип либо неизменяемый,
 * либо изменяемый, но сам сообщает об изменениях (как MutableState).
 *
 * List<T> нестабилен: под интерфейсом может лежать MutableList,
 * измениться он может, а сказать об этом — нет.
 */

/** Обёртка-обещание: «этот список больше не изменится». Аналог ImmutableList. */
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

/** true, если тип считается стабильным — то есть его equals можно доверять. */
private fun isStable(value: Any?): Boolean {
    return value == null || value is Int || value is String || value is Boolean || value is Immutable<*>
}
/**
 * Пропустить перезапуск можно, если оба значения стабильны и равны.
 *
 * @return true — параметр не изменился, перезапуск не нужен.
 */
fun <T> shouldSkip(old: T, new: T): Boolean {
    if (!isStable(old) || !isStable(new)) {
        return false
    }
    return old == new
}