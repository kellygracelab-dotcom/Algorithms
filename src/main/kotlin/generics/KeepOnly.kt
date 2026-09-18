package generics

/**
 * A homemade version of the standard filterIsInstance<T>(): pulls only the elements
 * of type T out of a list of mixed elements.
 *
 * Without inline + reified, `is T` here won't compile — the exact same reason we
 * covered: T is erased, and one check can't cover every possible type at once.
 *
 * TODO: add inline to the function and reified to T, implement the body — walk the
 *  list and keep only the elements for which `it is T`.
 */
inline fun <reified T> List<Any>.keepOnly(): List<T> {
    return filterIsInstance<T>()
}