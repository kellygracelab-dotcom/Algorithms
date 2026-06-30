package univesity.lab3

fun main() {
    println("=== Simple 1: Find_k_max ===")
    val arr1 = intArrayOf(10, 5, 12, 3, 2, 1, 8, 7, 9, 4)
    println("Array: ${arr1.toList()}")
    println("3 largest: ${findKMax(arr1, 3).toList()}")

    println("\n=== Simple 2: BuildHeapMax (1-based) ===")
    val arr2 = intArrayOf(10, 5, 12, 3, 2, 1, 8, 7, 9, 4)
    val heap2 = buildHeapMax(arr2)
    println("Source: ${arr2.toList()}")
    print("Max-heap: [_, ")
    for (i in 1..arr2.size) {
        print(heap2[i])
        if (i < arr2.size) print(", ")
    }
    println("]")

    println("\n=== Simple 3: DeleteTopMax ===")
    var size3 = arr2.size
    val (removed3, newSize3) = deleteTopMax(heap2, size3)
    size3 = newSize3
    println("Removed: $removed3")
    print("After: [_, ")
    for (i in 1..size3) {
        print(heap2[i])
        if (i < size3) print(", ")
    }
    println("]")

    println("\n=== Simple 4: BuildHeapMin (0-based) ===")
    val arr4 = intArrayOf(14, 15, 12, 35, 12, 11, 18, 21, 29)
    val heap4 = buildHeapMin(arr4)
    println("Source: ${arr4.toList()}")
    println("Min-heap: ${heap4.toList()}")

    println("\n=== Simple 5: DeleteTopMin ===")
    var size5 = arr4.size
    val (removed5, newSize5) = deleteTopMin(heap4, size5)
    size5 = newSize5
    println("Removed: $removed5")
    println("After: ${heap4.copyOf(size5).toList()}")

    println("\n=== HeapSort ===")
    val list = IntArrayList(20)
    list.fromArray(intArrayOf(10, 5, 12, 3, 2, 1, 8, 7, 9, 4))
    print("Before: "); list.printList()
    list.heapSort()
    print("After:  "); list.printList()
}