package com.zuzu.zuzanna_misztal_kalkulator_wtorek14

// Zuzanna Misztal, wtorek 14:00
// Wszystkie podstawowe punkty zostały zrealizowane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var equalsButton: Button
    internal lateinit var comaButton: Button
    internal lateinit var clearButton: Button
    internal lateinit var calcDisplay: TextView
    internal lateinit var digits: Array<Button>
    internal lateinit var operations: Array<Button>
    internal lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearButton = findViewById(R.id.clear)
        comaButton = findViewById(R.id.coma)
        equalsButton = findViewById(R.id.equals)
        calcDisplay = findViewById(R.id.display)
        calculator = Calculator(calcDisplay, this)

        val buttonsIDs = arrayOf(R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9)

        val operationsIDs = arrayOf(R.id.add, R.id.subtract, R.id.multiply, R.id.divide)

        digits = (buttonsIDs.map { id -> findViewById<Button>(id) }).toTypedArray()
        operations = (operationsIDs.map { id -> findViewById<Button>(id) }).toTypedArray()


        clearButton.setOnClickListener { calculator.clear() }
        digits.forEach { button -> button.setOnClickListener { i -> insertDigit(i as Button) } }
        operations.forEach { button -> button.setOnClickListener { i -> insertOperation(i as Button) } }
        equalsButton.setOnClickListener { calculator.evaluate() }
        comaButton.setOnClickListener { calculator.insertComa() }
    }

    private fun insertDigit(button: Button) {
        when (button.id) {
            R.id.button0 -> {
                calculator.insertDigit('0')
            }
            R.id.button1 -> {
                calculator.insertDigit('1')
            }
            R.id.button2 -> {
                calculator.insertDigit('2')
            }
            R.id.button3 -> {
                calculator.insertDigit('3')
            }
            R.id.button4 -> {
                calculator.insertDigit('4')
            }
            R.id.button5 -> {
                calculator.insertDigit('5')
            }
            R.id.button6 -> {
                calculator.insertDigit('6')
            }
            R.id.button7 -> {
                calculator.insertDigit('7')
            }
            R.id.button8 -> {
                calculator.insertDigit('8')
            }
            R.id.button9 -> {
                calculator.insertDigit('9')
            }
        }
    }

    private fun insertOperation(button: Button) {
        when (button.id) {
            R.id.add -> calculator.insertOperation(getString(R.string.add))
            R.id.subtract -> calculator.insertOperation(getString(R.string.subtract))
            R.id.multiply -> calculator.insertOperation(getString(R.string.multiply))
            R.id.divide -> calculator.insertOperation(getString(R.string.divide))
        }
    }
}