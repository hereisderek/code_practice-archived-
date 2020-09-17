package _2020._0917.moststonesremovedwithsameroworcolumn;

import util.*
import kotlin.system.measureNanoTime

/// https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/

/// WIP


interface S {
    fun removeStones(stones: Array<IntArray>): Int
}

private typealias Case = CheckPair<String, Int>
// private typealias Case = CheckPair<IntArray2D, Int>

fun main() {
    Solution1().apply {

        val case1 = CheckPair("[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]", 5)
        val case2 = CheckPair("[[0,0],[0,2],[1,1],[2,0],[2,2]]", 3)
        val case3 = CheckPair("[[0,0]]", 0)
        val case4 = CheckPair(
                "[[5,9],[9,0],[0,0],[7,0],[4,3],[8,5],[5,8],[1,1],[0,6],[7,5],[1,6],[1,9],[9,4],[2,8],[1,3],[4,2],[2,5],[4,1],[0,2],[6,5]]",
                5
        )

        // check(case1)
        // check(case2)
        // check(case3)
        check(case4)
    }

}



private fun S.check(case: Case) = case.apply{
    debugPrint("checking, input:$input... ", false)
    val time = measureNanoTime {
        val actual = removeStones(stringTo2DIntArray(input))
        debugPrint("expected:$expected, actual:$actual")
    }
    debugPrint("ended, took: $time nanosecond \n ------------------------------------------------")
}

// brute force
class Solution1 : S {
    override fun removeStones(stones: Array<IntArray>): Int {
        return move(stones, IntArray(stones.size), 0) - 1
    }

    private fun move(
            stones: Array<IntArray>,
            removedIndex: IntArray,
            counter: Int
    ) : Int {
        // all the stones have been removed
        if (!removedIndex.any { it == 0 }) return counter

        var max = 0
        removedIndex.forEachIndexed { index, i ->
            if (i == 0) {
                if (canRemove(stones, removedIndex, index)) {
                    val newRemovedIndex = removedIndex.clone().apply {
                        this[index] = 1
                    }
                    val newCounter = move(stones, newRemovedIndex, counter + 1)
                    // debugPrint("newCounter:$newCounter")
                    if (newCounter > max) {
                        max = newCounter
                    }
                } else return@forEachIndexed
            } else return@forEachIndexed
        }

        return max + 1
    }

    private fun canRemove(stones: Array<IntArray>, removedIndex: IntArray, toRemoveIndex: Int) : Boolean {
        // stone has already been removed
        if (removedIndex[toRemoveIndex] == 1) return false
        val stone = stones.getOrNull(toRemoveIndex) ?: error("unable to get stone at index:$toRemoveIndex, total:${stones.size}")
        val x = stone[0]
        val y = stone[1]


        // find if there's any other stone on the same row/column
        removedIndex.forEachIndexed { index, i ->
            // not the current stone
            if (index == toRemoveIndex) return@forEachIndexed
            // not the already removed stone
            if (removedIndex[index] == 1) return@forEachIndexed

            val otherStone = stones[index]
            if (x == otherStone[0] || y == otherStone[1]) return true
        }
        return false
    }

}