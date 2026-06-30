package org.example.leetcode.task19

fun main() {
    var node1: ListNode? = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4= ListNode(4)
    val node5 = ListNode(5)
    node1?.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5


    removeNthFromEnd(node1, 2)

    while (node1 != null) {
        println(node1.value)
        node1 = node1.next
    }
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0)
    dummy.next = head
    var current: ListNode? = dummy

    val length = getLength(head)
    val dif = length - n
    var i = 0

    while (current?.next != null) {
        if (i == dif) {
            current.next = current.next!!.next
            break
        }
        i++
        current = current.next
    }

    return dummy.next
}

fun getLength(head: ListNode?): Int {
    var count = 0
    var current = head
    while (current != null) {
        count++
        current = current.next
    }
    return count
}


class ListNode(var value: Int) {
    var next: ListNode? = null
}