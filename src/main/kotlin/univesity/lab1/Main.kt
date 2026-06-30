package univesity.lab1

import univesity.lab2.BBST
import kotlin.system.measureNanoTime

fun main() {
    val list = SortedLinkedList()
    list.read()

    list.printList()
    list.removeAverage()
    list.printList()
}

