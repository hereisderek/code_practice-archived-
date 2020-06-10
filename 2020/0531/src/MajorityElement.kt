import kotlin.test.assertEquals

// Majority Element
// https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3321/

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * */

fun main() {
    assertEquals(3, MajorityElement.majorityElement(intArrayOf(3, 2, 3)))
    assertEquals(2, MajorityElement.majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
}

object MajorityElement {

    /// sample
    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate: Int? = null
        for (num in nums) {
            if (count == 0) {
                candidate = num
            }
            count += if (num == candidate) 1 else -1
        }
        return candidate!!
    }


    /// my solutions:
    fun majorityElement4(nums: IntArray): Int {
        val counters = IntArray(nums.size)
        nums.forEachIndexed { _, num ->
            val index = nums.indexOfFirst { it == num }
            counters[index] += 1
        }
        var maxIndex = 0
        var max = -1
        counters.forEachIndexed { index, i ->
            if (i == 0) {
                return@forEachIndexed
            }
            if (i > max) {
                max = i
                maxIndex = index
            }
        }
        return nums[maxIndex]
    }


    fun majorityElement3(nums: IntArray): Int {
        val intMap = HashMap<Int, Int>(nums.size)
        nums.forEach {
            if (intMap.containsKey(it)) {
                intMap[it] = intMap[it]!! + 1
            } else {
                intMap[it] = 1
            }
        }
        val halfPoint = nums.size / 2f
        intMap.forEach {
            if (it.value > halfPoint) return it.key
        }
        return -1
    }

    fun majorityElement2(nums: IntArray): Int = nums.run {
        sort()
        nums[nums.size / 2]
    }
}