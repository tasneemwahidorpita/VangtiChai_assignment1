package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var tvEnteredAmount: TextView
    private lateinit var tvNote500: TextView
    private lateinit var tvNote100: TextView
    private lateinit var tvNote50: TextView
    private lateinit var tvNote20: TextView
    private lateinit var tvNote10: TextView
    private lateinit var tvNote5: TextView
    private lateinit var tvNote2: TextView
    private lateinit var tvNote1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
        observeViewModel()
    }

    private fun initViews() {
        tvEnteredAmount = findViewById(R.id.tvEnteredAmount)
        tvNote500 = findViewById(R.id.tvNote500)
        tvNote100 = findViewById(R.id.tvNote100)
        tvNote50 = findViewById(R.id.tvNote50)
        tvNote20 = findViewById(R.id.tvNote20)
        tvNote10 = findViewById(R.id.tvNote10)
        tvNote5 = findViewById(R.id.tvNote5)
        tvNote2 = findViewById(R.id.tvNote2)
        tvNote1 = findViewById(R.id.tvNote1)
    }

    private fun setupListeners() {
        val digitButtonIds = mapOf(
            R.id.btn0 to 0,
            R.id.btn1 to 1,
            R.id.btn2 to 2,
            R.id.btn3 to 3,
            R.id.btn4 to 4,
            R.id.btn5 to 5,
            R.id.btn6 to 6,
            R.id.btn7 to 7,
            R.id.btn8 to 8,
            R.id.btn9 to 9
        )

        for ((id, digit) in digitButtonIds) {
            findViewById<Button>(id)?.setOnClickListener {
                viewModel.appendDigit(digit)
            }
        }

        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            viewModel.clearAmount()
        }
    }

    private fun observeViewModel() {
        viewModel.amountStringLiveData.observe(this) { amountStr ->
            updateUI(amountStr)
        }
    }

    private fun updateUI(amountStr: String) {
        if (amountStr.isEmpty()) {
            tvEnteredAmount.text = getString(R.string.taka_prefix_empty)
        } else {
            tvEnteredAmount.text = getString(R.string.taka_prefix, amountStr)
        }

        val breakdown = viewModel.getNoteBreakdown()

        tvNote500.text = getString(R.string.note_format, 500, breakdown[500] ?: 0)
        tvNote100.text = getString(R.string.note_format, 100, breakdown[100] ?: 0)
        tvNote50.text = getString(R.string.note_format, 50, breakdown[50] ?: 0)
        tvNote20.text = getString(R.string.note_format, 20, breakdown[20] ?: 0)
        tvNote10.text = getString(R.string.note_format, 10, breakdown[10] ?: 0)
        tvNote5.text = getString(R.string.note_format, 5, breakdown[5] ?: 0)
        tvNote2.text = getString(R.string.note_format, 2, breakdown[2] ?: 0)
        tvNote1.text = getString(R.string.note_format, 1, breakdown[1] ?: 0)
    }
}
