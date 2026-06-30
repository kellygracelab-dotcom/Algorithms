package univesity.lab1

import kotlin.math.abs

fun SortedLinkedList.removeAverage(): Int {
    if (head == null) {
        throw NoSuchElementException()
    }

    var curr = head
    var sum = 0.0
    var count = 0

    while (curr != null) {
        sum += curr.value
        count++
        curr = curr.next
    }

    val average = sum / count

    var closestNode = head
    var closestPrev: Node? = null
    var prev: Node? = null
    curr = head

    while (curr != null) {
        if (abs(curr.value - average) < abs(closestNode!!.value - average)) {
            closestNode = curr
            closestPrev = prev
        }
        prev = curr
        curr = curr.next
    }

    val removedValue = closestNode!!.value

    if (closestPrev == null) {
        head = head?.next
    } else {
        closestPrev.next = closestNode.next
    }

    return removedValue
}

fun SortedLinkedList.mergeToNew(list2: SortedLinkedList): SortedLinkedList {
    val result = SortedLinkedList()

    var curr1 = head
    var curr2 = list2.head

    while (curr1 != null && curr2 != null) {
        if (curr1.value <= curr2.value) {
            result.add(Node(curr1.value))
            curr1 = curr1.next
        } else {
            result.add(Node(curr2.value))
            curr2 = curr2.next
        }
    }

    while (curr1 != null) {
        result.add(Node(curr1.value))
        curr1 = curr1.next
    }

    while (curr2 != null) {
        result.add(Node(curr2.value))
        curr2 = curr2.next
    }

    return result
}

fun SortedLinkedList.union(list2: SortedLinkedList): SortedLinkedList {
    val result = SortedLinkedList()

    var curr1 = head
    var curr2 = list2.head
    var lastAdded: Int? = null

    while (curr1 != null && curr2 != null) {
        val value = if (curr1.value < curr2.value) {
            val v = curr1.value
            curr1 = curr1.next
            v
        } else if (curr2.value < curr1.value) {
            val v = curr2.value
            curr2 = curr2.next
            v
        } else {
            val v = curr1.value
            curr1 = curr1.next
            curr2 = curr2.next
            v
        }

        if (lastAdded == null || lastAdded != value) {
            result.add(Node(value))
            lastAdded = value
        }
    }

    while (curr1 != null) {
        if (lastAdded == null || lastAdded != curr1.value) {
            result.add(Node(curr1.value))
            lastAdded = curr1.value
        }
        curr1 = curr1.next
    }

    while (curr2 != null) {
        if (lastAdded == null || lastAdded != curr2.value) {
            result.add(Node(curr2.value))
            lastAdded = curr2.value
        }
        curr2 = curr2.next
    }

    return result
}

fun SortedLinkedList.intersection(list2: SortedLinkedList): SortedLinkedList {
    val result = SortedLinkedList()

    var curr1 = head
    var curr2 = list2.head
    var lastAdded: Int? = null

    while (curr1 != null && curr2 != null) {
        if (curr1.value < curr2.value) {
            curr1 = curr1.next
        } else if (curr2.value < curr1.value) {
            curr2 = curr2.next
        } else {
            if (lastAdded == null || lastAdded != curr1.value) {
                result.add(Node(curr1.value))
                lastAdded = curr1.value
            }
            curr1 = curr1.next
            curr2 = curr2.next
        }
    }

    return result
}

fun SortedLinkedList.difference(list2: SortedLinkedList): SortedLinkedList {
    val result = SortedLinkedList()

    var curr1 = head
    var curr2 = list2.head
    var lastAdded: Int? = null

    while (curr1 != null && curr2 != null) {
        if (curr1.value < curr2.value) {
            if (lastAdded == null || lastAdded != curr1.value) {
                result.add(Node(curr1.value))
                lastAdded = curr1.value
            }
            curr1 = curr1.next
        } else if (curr2.value < curr1.value) {
            curr2 = curr2.next
        } else {
            val same = curr1.value
            while (curr1 != null && curr1.value == same) {
                curr1 = curr1.next
            }
            while (curr2 != null && curr2.value == same) {
                curr2 = curr2.next
            }
        }
    }

    while (curr1 != null) {
        if (lastAdded == null || lastAdded != curr1.value) {
            result.add(Node(curr1.value))
            lastAdded = curr1.value
        }
        curr1 = curr1.next
    }

    return result
}

fun SortedLinkedList.containsAll(list2: SortedLinkedList): Boolean {
    var curr1 = head
    var curr2 = list2.head

    while (curr1 != null && curr2 != null) {
        if (curr1.value < curr2.value) {
            curr1 = curr1.next
        } else if (curr2.value < curr1.value) {
            return false
        } else {
            curr1 = curr1.next
            curr2 = curr2.next
        }
    }

    return curr2 == null
}

fun SortedLinkedList.merge(list2: SortedLinkedList) {
    var curr1 = head
    var curr2 = list2.head
    var prev1: Node? = null

    while (curr1 != null && curr2 != null) {
        if (curr1.value <= curr2.value) {
            prev1 = curr1
            curr1 = curr1.next
        } else {
            val newNode = Node(curr2.value, curr1)

            if (prev1 == null) {
                head = newNode
            } else {
                prev1.next = newNode
            }

            prev1 = newNode
            curr2 = curr2.next
        }
    }

    while (curr2 != null) {
        val newNode = Node(curr2.value)

        if (prev1 == null) {
            head = newNode
        } else {
            prev1.next = newNode
        }

        prev1 = newNode
        curr2 = curr2.next
    }
}

// 1 -> 2 -> 3 -> 4 -> 5
fun SortedLinkedList.reverse() {
    var prev: Node? = null
    var curr = head

    while (curr != null) {
        val next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }

    head = prev
}

fun SortedLinkedList.reverseCopy(): SortedLinkedList {
    val l1 = SortedLinkedList()

    while (head != null) {
        val value = removeFirst()
        l1.addFirst(value)
    }

    return l1
}

fun SortedLinkedList.copyReverse(): SortedLinkedList {
    val result = SortedLinkedList()

    var curr = head
    while (curr != null) {
        val newNode = Node(curr.value)
        newNode.next = result.head
        result.head = newNode
        curr = curr.next
    }

    return result
}

fun SortedLinkedList.insertDuplicate() {
    var curr = head

    if (curr == null) {
        println("List is empty")
    } else {
        while (curr != null) {
            if (curr.next != null && curr.value == curr.next!!.value) {
                curr = curr.next!!.next
            } else {
                val next = curr.next
                curr.next = Node(curr.value, next)
                curr = curr.next!!.next
            }
        }
    }
}


fun SortedLinkedList.copyDuplicate(): SortedLinkedList {
    val result = SortedLinkedList()

    var curr = head

    if (curr == null) {
        return result
    } else {
        while (curr != null) {
            if (curr.next != null && curr.value == curr.next!!.value) {
                result.add(Node(curr.value))
                result.add(Node(curr.value))
                curr = curr.next!!.next
            } else {
                result.add(Node(curr.value))
                result.add(Node(curr.value))
                if (curr.next != null) {
                    curr = curr.next!!
                } else {
                    return result
                }
            }
        }
    }

    return result
}