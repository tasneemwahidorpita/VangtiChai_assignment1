package com.example.vangtichai

import org.junit.Assert.assertEquals
import org.junit.Test

class ChangeCalculatorTest {

    @Test
    fun testZeroAmount() {
        val breakdown = ChangeCalculator.calculateChange(0)
        assertEquals(0, breakdown[500])
        assertEquals(0, breakdown[100])
        assertEquals(0, breakdown[50])
        assertEquals(0, breakdown[20])
        assertEquals(0, breakdown[10])
        assertEquals(0, breakdown[5])
        assertEquals(0, breakdown[2])
        assertEquals(0, breakdown[1])
    }

    @Test
    fun testAmount6() {
        val breakdown = ChangeCalculator.calculateChange(6)
        assertEquals(0, breakdown[500])
        assertEquals(0, breakdown[100])
        assertEquals(0, breakdown[50])
        assertEquals(0, breakdown[20])
        assertEquals(0, breakdown[10])
        assertEquals(1, breakdown[5])
        assertEquals(0, breakdown[2])
        assertEquals(1, breakdown[1])
    }

    @Test
    fun testAmount68() {
        val breakdown = ChangeCalculator.calculateChange(68)
        assertEquals(0, breakdown[500])
        assertEquals(0, breakdown[100])
        assertEquals(1, breakdown[50])
        assertEquals(0, breakdown[20])
        assertEquals(1, breakdown[10])
        assertEquals(1, breakdown[5])
        assertEquals(1, breakdown[2])
        assertEquals(1, breakdown[1])
    }

    @Test
    fun testAmount688() {
        val breakdown = ChangeCalculator.calculateChange(688)
        assertEquals(1, breakdown[500])
        assertEquals(1, breakdown[100])
        assertEquals(1, breakdown[50])
        assertEquals(1, breakdown[20])
        assertEquals(1, breakdown[10])
        assertEquals(1, breakdown[5])
        assertEquals(1, breakdown[2])
        assertEquals(1, breakdown[1])
    }

    @Test
    fun testAmount6880() {
        val breakdown = ChangeCalculator.calculateChange(6880)
        assertEquals(13, breakdown[500])
        assertEquals(3, breakdown[100])
        assertEquals(1, breakdown[50])
        assertEquals(1, breakdown[20])
        assertEquals(1, breakdown[10])
        assertEquals(0, breakdown[5])
        assertEquals(0, breakdown[2])
        assertEquals(0, breakdown[1])
    }
}
