package univesity.lab3

fun findKMax(arr: IntArray, k: Int): IntArray {
    val heap = arr.copyOf()
    val n = heap.size
    for (i in n / 2 - 1 downTo 0) siftDownMax0(heap, i, n)
    val result = IntArray(minOf(k, n))
    var size = n
    for (i in result.indices) {
        result[i] = heap[0]
        heap[0] = heap[size - 1]
        size--
        siftDownMax0(heap, 0, size)
    }
    return result
}

fun buildHeapMax(source: IntArray): IntArray {
    val n = source.size
    val a = IntArray(n + 1)
    for (i in source.indices) a[i + 1] = source[i]
    for (i in n / 2 downTo 1) siftDownMax1(a, i, n)
    return a
}

fun deleteTopMax(heap: IntArray, n: Int): Pair<Int, Int> {
    val max = heap[1]
    heap[1] = heap[n]
    heap[n] = 0
    val newSize = n - 1
    if (newSize >= 1) siftDownMax1(heap, 1, newSize)
    return Pair(max, newSize)
}

fun buildHeapMin(source: IntArray): IntArray {
    val a = source.copyOf()
    val n = a.size
    for (i in n / 2 - 1 downTo 0) siftDownMin(a, i, n)
    return a
}

fun deleteTopMin(heap: IntArray, n: Int): Pair<Int, Int> {
    val min = heap[0]
    heap[0] = heap[n - 1]
    heap[n - 1] = 0
    val newSize = n - 1
    if (newSize >= 1) siftDownMin(heap, 0, newSize)
    return Pair(min, newSize)
}

private fun siftDownMax0(a: IntArray, i: Int, n: Int) {
    var idx = i
    while (true) {
        val left = 2 * idx + 1
        val right = 2 * idx + 2
        var largest = idx
        if (left < n && a[left] > a[largest]) largest = left
        if (right < n && a[right] > a[largest]) largest = right
        if (largest == idx) break
        val tmp = a[idx]; a[idx] = a[largest]; a[largest] = tmp
        idx = largest
    }
}

private fun siftDownMax1(a: IntArray, i: Int, n: Int) {
    var idx = i
    while (true) {
        val left = 2 * idx
        val right = 2 * idx + 1
        var largest = idx
        if (left <= n && a[left] > a[largest]) largest = left
        if (right <= n && a[right] > a[largest]) largest = right
        if (largest == idx) break
        val tmp = a[idx]; a[idx] = a[largest]; a[largest] = tmp
        idx = largest
    }
}

private fun siftDownMin(a: IntArray, i: Int, n: Int) {
    var idx = i
    while (true) {
        val left = 2 * idx + 1
        val right = 2 * idx + 2
        var smallest = idx
        if (left < n && a[left] < a[smallest]) smallest = left
        if (right < n && a[right] < a[smallest]) smallest = right
        if (smallest == idx) break
        val tmp = a[idx]; a[idx] = a[smallest]; a[smallest] = tmp
        idx = smallest
    }
}