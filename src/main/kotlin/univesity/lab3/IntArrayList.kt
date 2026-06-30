package univesity.lab3

class IntArrayList(private val capacity: Int = 100) {
    private var data: IntArray = IntArray(capacity)
    private var count: Int = 0

    fun isEmpty(): Boolean = count == 0
    fun isFull(): Boolean = count == capacity
    fun size(): Int = count

    fun add(value: Int): Boolean {
        if (isFull()) return false
        data[count++] = value
        return true
    }

    fun fromArray(arr: IntArray) {
        count = 0
        for (v in arr) add(v)
    }

    fun printList() {
        print("[")
        for (i in 0 until count) {
            print(data[i])
            if (i < count - 1) print(", ")
        }
        println("]")
    }

    private fun swap(i: Int, j: Int) {
        val tmp = data[i]
        data[i] = data[j]
        data[j] = tmp
    }

    private fun heapifyDown(i: Int, n: Int) {
        var largest = i
        val left = 2 * i + 1
        val right = 2 * i + 2
        if (left < n && data[left] > data[largest]) largest = left
        if (right < n && data[right] > data[largest]) largest = right
        if (largest != i) {
            swap(i, largest)
            heapifyDown(largest, n)
        }
    }

    private fun buildMaxHeap() {
        for (i in count / 2 - 1 downTo 0)
            heapifyDown(i, count)
    }

    fun heapSort() {
        buildMaxHeap()
        for (i in count - 1 downTo 1) {
            swap(0, i)
            heapifyDown(0, i)
        }
    }
}