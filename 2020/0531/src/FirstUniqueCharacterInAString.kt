import kotlin.test.assertEquals

// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3320/
// https://leetcode.com/submissions/detail/350249596/?from=/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3320/


/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * e.g.
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 */

fun main() {
    assertEquals(0, FirstUniqueCharacterInAString.firstUniqChar("leetcode"))
    assertEquals(2, FirstUniqueCharacterInAString.firstUniqChar("loveleetcode"))
}

object FirstUniqueCharacterInAString {
    fun firstUniqChar(s: String): Int {
        // counters for each letter
        val letters = IntArray(26) { 0 }

        // first occurring index of each letter
        val indexes = IntArray(26) { -1 }

        s.forEachIndexed { index, c ->
            val letterI = c -'a'
            letters[letterI]++
            if (indexes[letterI] == -1) {
                indexes[letterI] = index
            }
        }


        return s.mapNotNull {
            val letterI = it - 'a'
            if (letters[letterI] == 1) {
                indexes[letterI]
            } else null
        }.min() ?: -1
    }
}