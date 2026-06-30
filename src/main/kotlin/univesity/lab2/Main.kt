package univesity.lab2

fun main() {
    val bbst = BBST()

    bbst.insert(1)
    bbst.insert(10)
    bbst.insert(5)
    bbst.insert(9)
    bbst.insert(12)

    bbst.printTree()


    println(bbst.isBalanced())
}