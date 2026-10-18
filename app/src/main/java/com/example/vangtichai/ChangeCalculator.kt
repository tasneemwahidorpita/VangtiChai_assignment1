package com.example.vangtichai

/**
 * Utility object for calculating Bangladeshi Taka note breakdown.
 */
object ChangeCalculator {

    val DENOMINATIONS = listOf(500, 100, 50, 20, 10, 5, 2, 1)

    /**
     * Given an integer/long amount in Bangladeshi Taka,
     * returns a LinkedHashMap mapping each denomination to its note count.
     */
    fun calculateChange(amount: Long): Map<Int, Int> {
        val breakdown = LinkedHashMap<Int, Int>()
        var remaining = if (amount < 0) 0L else amount

        for (denom in DENOMINATIONS) {
            val count = (remaining / denom).toInt()
            breakdown[denom] = count
            remaining %= denom
        }

        return breakdown
    }
}
