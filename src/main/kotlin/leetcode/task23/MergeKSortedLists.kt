package org.example.leetcode.task23


fun main() {
    val a1 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(5)
        }
    }
    val b1 = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(6)
        }
    }

    var nodesArray: Array<ListNode?> = arrayOf(a1, b1)

    var result = mergeKLists(nodesArray)

    while (result != null) {
        print("${result.value} ")
        result = result.next
    }
}

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val dummy = ListNode(0)
    var current = dummy
    val curs = lists.copyOf()

    while (true) {
        var minIndex = -1
        var minVal = Int.MAX_VALUE
        for (i in curs.indices) {
            val node = curs[i] ?: continue
            if (node.value < minVal) {
                minVal = node.value
                minIndex = i
            }
        }
        if (minIndex == -1) break
        current.next = curs[minIndex]
        current = current.next!!
        curs[minIndex] = current.next
    }

    return dummy.next
}

class ListNode(var value: Int) {
    var next: ListNode? = null
}