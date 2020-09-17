package _2020._0501.week2

/**
 * Check If It Is a Straight Line
 * https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3323/
 * */

/// 01_CheckIfItIsAStraightLine.kt

fun main() {
    val plot = Plot(1, 1, 2, 2)
    print("online: ${plot.isOnLine(3, 3)}")
}

/*
* y = ax + b
* */
class Plot(val x0: Int, val y0: Int, val x1: Int, val y1: Int) {
    val a = (y1 - y0) / (x1 - x0).toDouble()
    val b = (x1 * y0 - x0 * y1) / (x1 - x0).toDouble()

    fun init() {
        println("a:$a, b:$b")
    }

    fun isOnLine(x: Int, y: Int) : Boolean = (y.toDouble() == (a * x + b))
}

class Solution {
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
        if (checkVerticalLine(coordinates)) return true
        val plot = Plot(coordinates[0][0], coordinates[0][1], coordinates[1][0], coordinates[1][1])
        coordinates.forEachIndexed { index, ints ->
            if (index <= 1) return@forEachIndexed
            if (!plot.isOnLine(ints[0], ints[1])) return false
        }
        return true
    }

    private fun checkVerticalLine(coordinates: Array<IntArray>) : Boolean {
        val x = coordinates[0][0]
        return coordinates.any { it[0] != x }
    }
}
