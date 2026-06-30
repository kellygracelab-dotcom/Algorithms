package univesity.lab1

class SortedLinkedList(
    var head: Node? = null,
) {

    fun removeByValue(currentValue: Int) {
        if (head == null) {
            throw NoSuchElementException()
        }

        var curr = head
        var prev = head

        if (currentValue == curr?.value) {
            head = head?.next
            return
        }

        while (true) {
            if (curr?.value == currentValue) {
                prev?.next = curr.next
                return
            } else {
                if (curr?.next == null) {
                    throw NoSuchElementException()
                }
                prev = curr
                curr = curr.next
            }
        }
    }

    fun removeByIndex(index: Int) {
        if (head == null) {
            throw IndexOutOfBoundsException()
        }
        if (index == 0) {
            head = head?.next
            return
        }
        var curr = head
        var prev = head
        var i = 0

        while (true) {
            if (i == index) {
                prev?.next = curr?.next
                return
            } else {
                if (curr?.next == null) {
                    throw IndexOutOfBoundsException()
                }
                prev = curr
                curr = curr.next
                i++
            }
        }
    }

    fun addFirst(value: Int) {
        val newNode = Node(value)
        newNode.next = head
        head = newNode
    }

    fun removeFirst(): Int {
        if (head == null) {
            throw NoSuchElementException()
        }

        val value = head!!.value
        head = head!!.next
        return value
    }


    fun printList() {
        var curr = head
        while (curr != null) {
            print("${curr.value} ")
            curr = curr.next
        }
        println()
    }

    fun add(newNode: Node) {
        if (head == null || newNode.value <= head!!.value) {
            newNode.next = head
            head = newNode
            return
        }

        var prev = head
        var curr = head?.next

        while (curr != null && curr.value < newNode.value) {
            prev = curr
            curr = curr.next
        }

        prev?.next = newNode
        newNode.next = curr
    }
}

class Node(
    var value: Int,
    var next: Node? = null
)