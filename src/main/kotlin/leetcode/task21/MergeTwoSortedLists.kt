package org.example.leetcode.task21

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
    var result = mergeTwoLists(a1, b1)

    while (result != null) {
        print("${result.value} ")
        result = result.next
    }
}

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var current = dummy
    var p1 = l1
    var p2 = l2

    while (p1 != null && p2 != null) {
        if (p1.value < p2.value) {
            current.next = p1
            p1 = p1.next
        } else {
            current.next = p2
            p2 = p2.next
        }
        current = current.next!!
    }

    current.next = p1 ?: p2

    return dummy.next
}

class ListNode(var value: Int) {
    var next: ListNode? = null
}