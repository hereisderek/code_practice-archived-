import kotlin.test.assertEquals

/**
 *
 * https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3317/
 *
 *
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 *
 * my solution: https://leetcode.com/submissions/detail/349774646/?from=/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3317/
 */


fun main() {
    JewelsAndStonesSolution().apply {
        assertEquals(numJewelsInStones("aA", "aAAbbbb"), 3)

    }

}
class JewelsAndStonesSolution {
    fun numJewelsInStones(J: String, S: String): Int {
        val jArray = J.toCharArray()
        val sArray = S.toCharArray()
        var counter = 0

        for (s in sArray) {
            for (j in jArray) {
                if (s == j) {
                    counter++
                    break
                }
            }
        }

        return counter
    }


    fun numJewelsInStones2(J: String, S: String): Int = IntArray('z'.toInt()+ 1)
            .apply { S.forEach { this[it.toInt()]++ } }
            .run { J.sumBy { this[it.toInt()] } }
}