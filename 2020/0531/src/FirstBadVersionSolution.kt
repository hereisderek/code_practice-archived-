import kotlin.random.Random


// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3316/
// https://leetcode.com/articles/first-bad-version/

fun main() {
    (4000 until 8000).forEach { i ->
        val n = Random.nextInt(1, i)
        val b = Random.nextInt(1, n + 1)
        Solution(n, b).result()
    }
}


abstract class VersionControl (private val n : Int, private val badVersion : Int) {
    private var callCounter = 0


    fun isBadVersion(version: Int): Boolean {
        callCounter += 1
        return version >= badVersion
    }

    fun result(){
        val calculateResult = firstBadVersion(n)
        val outPut = "n:$n, expect:$badVersion, actual:$calculateResult, called:$calculateResult times"
        if (badVersion == calculateResult) {
            println(outPut)
        } else {
            System.err.println(outPut)
        }
    }

    abstract fun firstBadVersion(n: Int) : Int
}

class Solution(n : Int, badVersion : Int): VersionControl(n, badVersion) {
    companion object {
        fun findHalfRadius(radius: Int) = kotlin.math.ceil(radius / 2.0).toInt()
    }


    override fun firstBadVersion(n: Int) : Int {
        val radius = findHalfRadius(n - 1)
        val version = 1 + radius
        val result = isBadVersion(version)
        return checkVersion(result, version, radius).also {
//            println("final result: $it")
        }
    }



    private fun checkVersion(lastResult: Boolean, version: Int, radius: Int) : Int {
//        println("\tcheckVersion lastResult:$lastResult, version:$version, radius:$radius")
        return when{
            lastResult && radius <= 1 -> {
                val v = version - 1
                val result = isBadVersion(v)
                checkVersion(result, v, 1)
            }
            !lastResult && radius <= 1 -> {
                version + 1
            }
            else -> {
                val newRadius = findHalfRadius(radius)
                val newVersion = if (lastResult) {
                    version - newRadius
                } else {
                    version + newRadius

                }
                val result = isBadVersion(newVersion)
                checkVersion(result, newVersion, newRadius)
            }
        }
    }

}