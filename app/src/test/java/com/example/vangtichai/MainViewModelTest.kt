package com.example.vangtichai

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testAppendDigitsSequence() {
        val savedStateHandle = SavedStateHandle()
        val viewModel = MainViewModel(savedStateHandle)

        assertEquals("", viewModel.amountString)

        viewModel.appendDigit(2)
        assertEquals("2", viewModel.amountString)

        viewModel.appendDigit(3)
        assertEquals("23", viewModel.amountString)

        viewModel.appendDigit(4)
        assertEquals("234", viewModel.amountString)

        val breakdown = viewModel.getNoteBreakdown()
        assertEquals(0, breakdown[500])
        assertEquals(2, breakdown[100])
        assertEquals(0, breakdown[50])
        assertEquals(1, breakdown[20])
        assertEquals(1, breakdown[10])
        assertEquals(0, breakdown[5])
        assertEquals(2, breakdown[2])
        assertEquals(0, breakdown[1])
    }

    @Test
    fun testLeadingZerosHandling() {
        val savedStateHandle = SavedStateHandle()
        val viewModel = MainViewModel(savedStateHandle)

        viewModel.appendDigit(0)
        viewModel.appendDigit(0)
        assertEquals("", viewModel.amountString)

        viewModel.appendDigit(7)
        assertEquals("7", viewModel.amountString)
    }

    @Test
    fun testMaxDigitsLimitAndOverflowPrevention() {
        val savedStateHandle = SavedStateHandle()
        val viewModel = MainViewModel(savedStateHandle)

        repeat(15) {
            viewModel.appendDigit(9)
        }

        assertEquals("999999999999", viewModel.amountString)
        assertEquals(12, viewModel.amountString.length)
        assertEquals(999999999999L, viewModel.getAmountAsLong())
    }

    @Test
    fun testClearAmount() {
        val savedStateHandle = SavedStateHandle()
        val viewModel = MainViewModel(savedStateHandle)

        viewModel.appendDigit(6)
        viewModel.appendDigit(8)
        assertEquals("68", viewModel.amountString)

        viewModel.clearAmount()
        assertEquals("", viewModel.amountString)
        assertEquals(0L, viewModel.getAmountAsLong())
    }

    @Test
    fun testSavedStatePersistence() {
        val savedStateHandle = SavedStateHandle()
        val viewModel1 = MainViewModel(savedStateHandle)

        viewModel1.appendDigit(6)
        viewModel1.appendDigit(8)
        viewModel1.appendDigit(8)

        val viewModel2 = MainViewModel(savedStateHandle)
        assertEquals("688", viewModel2.amountString)
        assertEquals(688L, viewModel2.getAmountAsLong())
    }
}
