package univesity.lab2

class BBST {

    private class Node(
        var key: Int,
        var left: Node? = null,
        var right: Node? = null,
        var height: Int = 1
    )

    private var root: Node? = null

    fun findMiddle(): Int {
        if (root == null) return -10000
        val keyMin = findMin(root!!).key
        val keyMax = findMax(root!!).key
        val valueMid = (keyMin + keyMax) / 2.0
        return findClosest(root, valueMid, root!!.key)
    }

    private fun findClosest(node: Node?, target: Double, best: Int): Int {
        if (node == null) return best
        val newBest = if (kotlin.math.abs(node.key - target) < kotlin.math.abs(best - target)) node.key else best
        return when {
            target < node.key -> findClosest(node.left, target, newBest)
            target > node.key -> findClosest(node.right, target, newBest)
            else -> node.key
        }
    }

    fun copyBBST(): BBST {
        val copy = BBST()
        copy.root = copyNode(root)
        return copy
    }

    private fun copyNode(node: Node?): Node? {
        if (node == null) return null
        val newNode = Node(node.key)
        newNode.height = node.height
        newNode.left = copyNode(node.left)
        newNode.right = copyNode(node.right)
        return newNode
    }

    fun equalsBBST(other: BBST): Boolean {
        return equalsNode(root, other.root)
    }

    private fun equalsNode(a: Node?, b: Node?): Boolean {
        if (a == null && b == null) return true
        if (a == null || b == null) return false
        if (a.key != b.key) return false
        return equalsNode(a.left, b.left) && equalsNode(a.right, b.right)
    }

    fun isBalanced(): Boolean {
        return checkBalanced(root) != -1
    }

    private fun checkBalanced(node: Node?): Int {
        if (node == null) return 0
        val lh = checkBalanced(node.left)
        if (lh == -1) return -1
        val rh = checkBalanced(node.right)
        if (rh == -1) return -1
        if (kotlin.math.abs(lh - rh) > 1) return -1
        return maxOf(lh, rh) + 1
    }

    fun commonAncestor(a: Int, b: Int): Int {
        val result = commonAncestor(root, a, b)
        return result?.key ?: -10000
    }

    private fun commonAncestor(node: Node?, a: Int, b: Int): Node? {
        if (node == null) return null
        if (a < node.key && b < node.key) return commonAncestor(node.left, a, b)
        if (a > node.key && b > node.key) return commonAncestor(node.right, a, b)
        return node
    }

    fun deleteEven() {
        val keys = mutableListOf<Int>()
        collectKeys(root, keys)
        for (key in keys) {
            if (key % 2 == 0) delete(key)
        }
    }

    private fun collectKeys(node: Node?, list: MutableList<Int>) {
        if (node == null) return
        collectKeys(node.left, list)
        list.add(node.key)
        collectKeys(node.right, list)
    }

    fun findSecondLargest(): Int {
        return if (root == null) {
            -1
        } else {
            if (root!!.left == null && root!!.right == null) {
                return -1
            }
            findSecondLargest(root!!, root!!)
        }
    }

    private fun findSecondLargest(node: Node, parent: Node): Int {
        if (node.left == null && node.right == null) {
            return parent.key
        }

        if (node.right == null) {
            return findMax(node.left!!).key
        }

        if (node.left == null) {
            return node.key
        }

        val secondLargest = findSecondLargest(node.right!!, node)

        return secondLargest
    }

    fun fatherNode(key: Int): Int {
        if (root == null) return -10000
        if (root!!.key == key) return -10000
        return fatherNode(root!!, key)
    }

    private fun fatherNode(node: Node, key: Int): Int {

        if (node.left != null) {
            if (node.left!!.key == key) {
                return node.key
            }
        }

        if (node.right != null) {
            if (node.right!!.key == key) {
                return node.key
            }
        }

        val left = node.left?.let { fatherNode(it, key) } ?: -10000
        val right = node.right?.let { fatherNode(it, key) } ?: -10000

        if (left != -10000) {
            return left
        }

        if (right != -10000) {
            return right
        }

        return -10000
    }

    fun printSorted() {
        print("First line: keys in ascending order: ")
        printAscending(root)
        println()
        print("Second line: keys in descending order: ")
        printDecreasing(root)
    }

    private fun printAscending(node: Node?) {
        if (node == null) return

        printAscending(node.left)
        print("${node.key} ")
        printAscending(node.right)
    }

    private fun printDecreasing(node: Node?) {
        if (node == null) return

        printDecreasing(node.right)
        print("${node.key} ")
        printDecreasing(node.left)
    }


    private fun sumKeys(node: Node?): Int {
        if (node == null) return 0

        val left = sumKeys(node.left)
        val right = sumKeys(node.right)
        val myContribution = if (node.right != null) node.right!!.key else 0

        return left + right + myContribution
    }

    private fun countNode(node: Node?): Int {
        if (node == null) return 0

        val left = countNode(node.left)
        val right = countNode(node.right)
        val myContribution = if (node.left != null) 1 else 0

        return left + right + myContribution
    }

    fun delete(key: Int) {
        root = delete(root, key)
    }

    private fun delete(node: Node?, key: Int): Node? {
        if (node == null) {
            return null
        }
        if (node.key == key) {
            if (node.left == null && node.right == null) {
                return null
            }
            if (node.left == null) {
                return node.right
            }
            if (node.right == null) {
                return node.left
            }
            val min = findMin(node.right!!)
            node.key = min.key
            node.right = delete(node.right, min.key)
        }

        if (node.key > key) {
            node.left = delete(node.left, key)
        }
        if (node.key < key) {
            node.right = delete(node.right, key)
        }

        updateHeight(node)
        val balance = balanceFactor(node)
        if (balance > 1 && balanceFactor(node.left!!) >= 0) {
            return rotateRight(node)
        }

        if (balance > 1 && balanceFactor(node.left!!) < 0) {
            node.left = rotateLeft(node.left!!)
            return rotateRight(node)
        }

        if (balance < -1 && balanceFactor(node.right!!) <= 0) {
            return rotateLeft(node)
        }

        if (balance < -1 && balanceFactor(node.right!!) > 0) {
            node.right = rotateRight(node.right!!)
            return rotateLeft(node)
        }

        return node
    }

    private fun findMax(node: Node): Node {
        var current = node

        while (current.right != null) {
            current = current.right!!
        }

        return current
    }

    private fun findMin(node: Node): Node {
        var current = node

        while (current.left != null) {
            current = current.left!!
        }

        return current
    }

    fun search(key: Int): Boolean {
        return search(root, key)
    }

    private fun search(node: Node?, key: Int): Boolean {
        if (node == null) return false

        if (node.key == key) return true

        if (node.key > key) {
            return search(node.left, key)
        }

        return search(node.right, key)
    }

    fun insert(key: Int) {
        root = insert(root, key)
    }

    private fun insert(node: Node?, key: Int): Node {
        if (node == null) {
            return Node(key)
        } else {
            if (node.key > key) {
                node.left = insert(node.left, key)
            } else if (node.key < key) {
                node.right = insert(node.right, key)
            }
        }

        updateHeight(node)
        val balance = balanceFactor(node)
        if (balance > 1 && key < node.left!!.key) {
            return rotateRight(node)
        }
        if (balance > 1 && key > node.left!!.key) {
            node.left = rotateLeft(node.left!!)
            return rotateRight(node)
        }
        if (balance < -1 && key > node.right!!.key) {
            return rotateLeft(node)
        }
        if (balance < -1 && key < node.right!!.key) {
            node.right = rotateRight(node.right!!)
            return rotateLeft(node)
        }

        return node
    }

    fun printTree() {
        printTree(root, 0)
        println()
    }

    private fun printTree(node: Node?, level: Int) {
        if (node == null) return

        printTree(node.right, level + 1)

        repeat(level) {
            print("    ")
        }
        println(node.key)

        printTree(node.left, level + 1)
    }

    private fun rotateRight(node: Node): Node {
        val leftChild = node.left!!
        val leftRightChild = leftChild.right

        leftChild.right = node
        node.left = leftRightChild

        updateHeight(node)
        updateHeight(leftChild)

        return leftChild
    }

    private fun rotateLeft(node: Node): Node {
        val rightChild = node.right!!
        val rightLeftChild = rightChild.left

        rightChild.left = node
        node.right = rightLeftChild

        updateHeight(node)
        updateHeight(rightChild)

        return rightChild
    }

    private fun updateHeight(node: Node) {
        node.height = 1 + maxOf(height(node.left), height(node.right))
    }

    private fun balanceFactor(node: Node): Int {
        return height(node.left) - height(node.right)
    }

    private fun height(node: Node?): Int {
        return node?.height ?: 0
    }
}