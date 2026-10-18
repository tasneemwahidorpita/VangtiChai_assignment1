package com.example.vangtichai

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val KEY_AMOUNT = "key_amount_string"
        private const val MAX_DIGITS = 12
    }

    // LiveData for observing amount changes across UI updates and orientation changes
    val amountStringLiveData: LiveData<String> = state.getLiveData(KEY_AMOUNT, "")

    val amountString: String
        get() = state.get<String>(KEY_AMOUNT) ?: ""

    /**
     * Appends a digit (0-9) to the right of current amount string.
     */
    fun appendDigit(digit: Int) {
        if (digit !in 0..9) return
        val current = amountString

        if (current.isEmpty() || current == "0") {
            if (digit == 0) {
                state[KEY_AMOUNT] = ""
            } else {
                state[KEY_AMOUNT] = digit.toString()
            }
        } else {
            if (current.length < MAX_DIGITS) {
                state[KEY_AMOUNT] = current + digit
            }
        }
    }

    /**
     * Resets the amount to empty.
     */
    fun clearAmount() {
        state[KEY_AMOUNT] = ""
    }

    /**
     * Returns numerical amount as Long.
     */
    fun getAmountAsLong(): Long {
        return amountString.toLongOrNull() ?: 0L
    }

    /**
     * Computes the current note breakdown.
     */
    fun getNoteBreakdown(): Map<Int, Int> {
        return ChangeCalculator.calculateChange(getAmountAsLong())
    }
}
