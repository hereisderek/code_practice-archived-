package _2020._0917.redundant_connection_i

import java.io.PrintStream


// https://leetcode-cn.com/problems/redundant-connection-ii

// WIP

private typealias Input = Array<IntArray>
val IntArray.content : String get() = joinToString(",", "[", "]")
val IntArray.u : Int get() = this[0]
val IntArray.v : Int get() = this[1]

val Input.content : String get() = this.joinToString(",", "[", "]"){ it.content }

fun errorLn(out: String, newLine: Boolean = true) = System.err.print(out, newLine)

fun outLn(out: String, newLine: Boolean = true) = System.out.print(out, newLine)

fun PrintStream.print(out: String, newLine: Boolean = true) {
    if (newLine) println(out) else print(out)
    // flush()
}

inline fun check(expected: IntArray, input: Input, calculate: (input: Input)-> IntArray) {
    println("checking input of: ${input.content} ...")
    try {
        val actual = calculate(input)
        "expected:${expected.content} vs actual:${actual.content}".also {
            if (actual.contentEquals(expected)) { outLn(it) } else { errorLn(it) }
        }

    } catch (e: Exception) {
        errorLn("failed for input: ${input.content}, $e")
    }
}



fun main() {

    Solution().apply {
        check(
                intArrayOf(2, 3),
                arrayOf(
                        intArrayOf(1, 2),
                        intArrayOf(1, 3),
                        intArrayOf(2, 3)
                ),
                ::findRedundantDirectedConnection
        )

        check(
                intArrayOf(4, 1),
                arrayOf(
                        intArrayOf(1, 2),
                        intArrayOf(2, 3),
                        intArrayOf(3, 4),
                        intArrayOf(4, 1),
                        intArrayOf(1, 5)
                ),
                ::findRedundantDirectedConnection
        )

        check(
                intArrayOf(2, 1),
                arrayOf(
                        intArrayOf(2, 1),
                        intArrayOf(3, 1),
                        intArrayOf(4, 2),
                        intArrayOf(1, 4)
                ),
                ::findRedundantDirectedConnection
        )
    }
}





/// =======
class Solution2 {
    companion object {
        const val INITIAL_VALUE = -1
    }

    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val inTable = IntArray(edges.size + 1)
        val outTable = IntArray(edges.size + 1)
        var resultIndex: Int? = null


        do {
            edges.forEachIndexed { index, it ->
                outTable[it.u] ++
                inTable[it.v] ++

                if (outTable[it.v] != 0) {
                    resultIndex = index
                }

            }
            /*edges.forEach {
                outTable[it.u] ++
                inTable[it.v] ++
                if (inTable[it.v] != 0) {
                    result = it
                }
            }*/
        } while (resultIndex == null)
        return edges[resultIndex!!]
    }
}


/// =======
class Solution {
    companion object {
        const val INITIAL_VALUE = -1
    }

    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val values = IntArray(edges.size + 1){ INITIAL_VALUE }

        var result : IntArray? = null

        edges.forEach {
            val u = it[0]
            val v = it[1]

            val currentParentForV = values[v]
            if (currentParentForV == INITIAL_VALUE) {


                print("checking if current v:$v is an ancestor of u:$u... ")
                if (isAncestor(values, u, v)) {
                    result = it
                    println("and it actually is, updating result:${it.content}")
                    return it
                } else {
                    println("nope, continue...")
                }

                values[v] = u
            } else {
                errorLn("trying to override existing u: $currentParentForV for v:$v with new u:$u")
                result = it
                return it
            }
        }

        return result ?: error("not found")
    }

    private fun isAncestor(data: IntArray, v: Int, u: Int) : Boolean {
        return when(val currentU = data[v]){
            INITIAL_VALUE -> false
            u -> true
            v -> true
            else -> isAncestor(data, currentU, u)
        }
    }
}