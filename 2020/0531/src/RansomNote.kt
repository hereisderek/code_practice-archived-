// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/
// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/
// https://leetcode.com/submissions/detail/349780852/?from=/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/
// https://leetcode.com/submissions/detail/349783672/?from=/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3318/


fun main() {
    RansomNote().apply {
        assert(!canConstruct("a", "b"))
        assert(!canConstruct("aa", "ab"))
        assert(canConstruct("aa", "aab"))
    }

}


class RansomNote {

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val rChars = IntArray('z'.toInt() + 1)
        magazine.forEach {
            rChars[it.toInt()] += 1
        }

        ransomNote.toCharArray().forEach {
            val charI = it.toInt()
            if (rChars[charI] <= 0) return false
            rChars[charI] -= 1
        }
        return true
    }


    fun canConstruct2(ransomNote: String, magazine: String): Boolean {
        val magArr = IntArray(26)
        for(letter in 0 until magazine.length) {
            magArr[magazine[letter] - 'a']++
        }

        for (letter in 0 until ransomNote.length) {
            val index = ransomNote[letter] - 'a';
            if (magArr[index] <= 0) {
                return false
            } else {
                magArr[index]--
            }
        }
        return true
    }
}