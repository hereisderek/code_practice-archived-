package _2020._0501.addtwonumbers

// https://leetcode-cn.com/problems/add-two-numbers/submissions/

fun main() {
    val solution = Solution()
    val node1 = getListNode(5)
    val node2 = getListNode(5)
//    val node1 = getListNode(2, 4, 3)
//    val node2 = getListNode(5, 6, 4)
    val result = solution.addTwoNumbers(node1, node2)
    println("result: $result")
}

private fun getListNode(vararg values: Int) : ListNode? {
    var firstNode : ListNode? = null
    var lastNode: ListNode? = null
    values.forEachIndexed { index, i ->
        if (index == 0) {
            firstNode = ListNode(i, null)
            lastNode = firstNode
        } else {
            lastNode?.next = ListNode(i, null)
            lastNode = lastNode?.next
        }
    }
    return firstNode
}


data class ListNode(var value: Int, var next: ListNode?) {
    override fun toString(): String {
        return if (next == null) value.toString() else "$value,$next"
    }
}

class CarryOverValue(value: Int) {
    var digit: Int = 0; private set
    var carryOver: Int = 0; private set
    var value = value; set(value) {
        field = value
        digit = value % 10
        carryOver = (value - digit) / 10
        println("value set:$value, digit:$digit, carryOver:$carryOver")
    }
}

class Solution {
    val carryOverValue = CarryOverValue(0)

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var pointer1 = l1
        var pointer2 = l2
        var carryOverInt = 0
        var firstResult : ListNode? = null
        var lastPointer : ListNode? = null

        while (pointer1 != null || pointer2 != null || carryOverInt != 0) {
            val valueInt = (pointer1?.value ?: 0) + (pointer2?.value ?: 0) + carryOverInt

            carryOverValue.apply {
                value = valueInt
                println("new node digit:$digit, lastPointer:${lastPointer?.value}")

                ListNode(digit, null).also {
                    if (firstResult == null) {
                        firstResult = it
                    }
                    lastPointer?.next = it
                    lastPointer = it
                }

                carryOverInt = carryOver
            }

            pointer1 = pointer1?.next
            pointer2 = pointer2?.next
        }
        return firstResult
    }
}