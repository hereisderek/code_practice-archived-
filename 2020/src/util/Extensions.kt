package util

import java.util.*
import kotlin.collections.ArrayList


val IntArray.content : String get() = joinToString(",", "[", "]")
val IntArray2D.content : String get() = this.joinToString(",", "[", "]"){ it.content }


typealias IntArray2D = Array<IntArray>


data class CheckPair<InputType, OutputType>(val input: InputType, val expected: OutputType)

const val DEBUG = true

fun debugPrint(out: String, newLine: Boolean = true) {
    if (!DEBUG) return
    if (newLine) println(out) else debugPrint(out)
}

fun <InputType, OutputType>check(
        input: InputType,
        expected: OutputType,
        checker: ((expected: OutputType, actual: OutputType)->Boolean)? = null,
        func: (input: InputType)->OutputType
) {
    debugPrint("checking, input:${input}")
}

fun <InputType, OutputType>check(
        pair: CheckPair<InputType, OutputType>,
        checker: ((expected: OutputType, actual: OutputType)->Boolean)? = null,
        func: (input: InputType)->OutputType
) = check(pair.input, pair.expected, checker, func)

// [[5,9],[9,0],[0,0],[7,0],[4,3],[8,5],[5,8],[1,1],[0,6],[7,5],[1,6],[1,9],[9,4],[2,8],[1,3],[4,2],[2,5],[4,1],[0,2],[6,5]]
fun stringTo2DIntArray(input: String) : IntArray2D = ArrayList<IntArray>().apply {
    val scanner = Scanner(input).useDelimiter("([^0-9])+").apply {
        while (true) {
            val first = if (hasNextInt()) nextInt() else return@apply
            val second = if (hasNextInt()) nextInt() else return@apply
            add(intArrayOf(first, second))
        }
    }
}.run { Array<IntArray>(size){ get(it) } }