import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow
import kotlin.test.assertEquals

// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3319/
// https://leetcode.com/submissions/detail/350146988/?from=/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3319/

fun main() {
    println("NumberComplement.findComplement(2147483647):" + NumberComplement.findComplement(2147483647))
    assertEquals(1, NumberComplement.findComplement(2))
    assertEquals(2, NumberComplement.findComplement(5))
    assertEquals(0, NumberComplement.findComplement(1))
}

object NumberComplement {
    fun findComplement(num: Int): Int {
        val power = log2(num.toDouble()).toInt() + 1
        return ((1 shl power) - 1 - num).also {
//        return (2.0.pow(power) - 1 - num).toInt().also {
            println("found power:$power that's >= $num, result:$it")
        }
    }

    fun findComplement2(num: Int): Int {
        var p = ceil(log2(num.toDouble())).toInt()
        if((1 shl p) == num) p++
        return num xor ((1 shl p) - 1)
    }

}