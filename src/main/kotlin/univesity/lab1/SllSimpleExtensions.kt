package univesity.lab1

fun listEqual(list1: SortedLinkedList, list2: SortedLinkedList): Boolean {
    list1.printList()
    list2.printList()

    var curr1 = list1.head
    var curr2 = list2.head

    while (curr1 != null && curr2 != null) {
        if (curr1.value == curr2.value) {
            curr1 = curr1.next
            curr2 = curr2.next
        } else {
            println("${curr1.value} != ${curr2.value}")
            return false
        }
    }

    return curr1 == null && curr2 == null
}

fun SortedLinkedList.splitAt(loc: Int): Pair<SortedLinkedList, SortedLinkedList> {
    if (loc < 0) {
        throw IndexOutOfBoundsException()
    }

    var i = 0
    var curr = head

    val first = SortedLinkedList()
    val second = SortedLinkedList()

    while (i < loc) {
        if (curr != null) {
            first.add(Node(curr.value))
            curr = curr.next
            i++
        } else {
            throw IndexOutOfBoundsException()
        }
    }

    while (curr != null) {
        second.add(Node(curr.value))
        curr = curr.next
    }

    return Pair(first, second)
}


fun SortedLinkedList.copyRange(k: Int, j: Int): SortedLinkedList {
    if (k < 0 || j < 0 || k > j) {
        throw IndexOutOfBoundsException()
    }

    var i = 0
    var curr = head
    val result = SortedLinkedList()

    while (i < k) {
        if (curr != null) {
            curr = curr.next
            i++
        } else {
            throw IndexOutOfBoundsException()
        }
    }

    while (i <= j) {
        if (curr != null) {
            result.add(Node(curr.value))
            curr = curr.next
            i++
        } else {
            throw IndexOutOfBoundsException()
        }
    }

    return result
}

fun SortedLinkedList.printReverse() {
    printReverseRecursive(head)
    println()
}

private fun printReverseRecursive(node: Node?) {
    if (node == null) return
    printReverseRecursive(node.next)
    print("${node.value} ")
}

fun SortedLinkedList.read() {
    head = null
    println("Input numbers by space, when you finished click enter")
    readln()
        .split(" ")
        .filter { it.isNotBlank() }
        .forEach {
            val number = it.toIntOrNull()
            if (number != null) {
                add(Node(number))
            }
        }
}

fun SortedLinkedList.removeDuplicates() {
    var curr = head

    while (curr != null && curr.next != null) {
        if (curr.value == curr.next!!.value) {
            curr.next = curr.next?.next
        } else {
            curr = curr.next
        }
    }
}

fun SortedLinkedList.sumEvenItems(): Int {
    return sumEvenItemsRecursive(head)
}

private fun sumEvenItemsRecursive(node: Node?): Int {
    if (node == null) {
        return 0
    }

    return if (node.value % 2 == 0) {
        node.value + sumEvenItemsRecursive(node.next)
    } else {
        sumEvenItemsRecursive(node.next)
    }
}

fun SortedLinkedList.sumItems(): Int {
    return sumItemsRecursive(head)
}

private fun sumItemsRecursive(node: Node?): Int {
    if (node == null) {
        return 0
    }

    return node.value + sumItemsRecursive(node.next)
}


fun SortedLinkedList.search(item: Int): Boolean {
    return searchRecursive(head, item)
}

private fun searchRecursive(node: Node?, item: Int): Boolean {
    if (node == null) {
        return false
    }
    return if (node.value == item) {
        true
    } else if (node.value > item) {
        false
    } else {
        searchRecursive(node.next, item)
    }
}