package com.zuzu.zuzanna_misztal_kalkulator_wtorek14

import android.content.Context
import android.widget.TextView

class Calculator(var display: TextView, var context: Context): CalculatorInterface {

    var comaUsed = false
    var afterEvaluation = false

    override var firstNumber: String = ""
        set(value) {
            field = value
            display.text = value
        }

    override var secondNumber: String = ""
        set(value) {
            field = value
            display.text = firstNumber + operation + value
        }

    override var operation: String = ""
        set(value) {
            field = value
            display.text = firstNumber + value
        }

    override fun insertDigit(digit: Char) {
        when {
            afterEvaluation -> {
                clear()
                afterEvaluation = false
                insertDigit(digit)
            }
            operation.isEmpty() -> {
                firstNumber += digit
            }
            else -> {
                secondNumber += digit
            }
        }
    }

    override fun insertOperation(operation: String) {
        if (secondNumber == "") {
            this.operation = operation
        } else {
            firstNumber = quickEval(firstNumber, this.operation, secondNumber)
            secondNumber = ""
            this.operation = operation
        }
        comaUsed = false
        afterEvaluation = false
    }

    override fun clear() {
        operation = ""
        secondNumber = ""
        firstNumber = ""
        comaUsed = false
    }

    override fun insertComa() {
        if (afterEvaluation) {
            clear()
            afterEvaluation = false
            insertComa()
        } else if (firstNumber.isEmpty()) {
            firstNumber = "0."
            comaUsed = true
        } else if (operation.isEmpty() and !comaUsed) {
            firstNumber += "."
            comaUsed = true
        } else if (operation.isNotEmpty() and secondNumber.isEmpty()) {
            secondNumber = "0."
            comaUsed = true
        } else if (!comaUsed) {
            secondNumber += "."
            comaUsed = true
        }
    }

    override fun evaluate() {
        if (secondNumber.isNotEmpty()) {
            firstNumber = quickEval(firstNumber, operation, secondNumber)
            operation = ""
            secondNumber = ""
        } else {
            operation = ""
        }
        afterEvaluation = true
    }

    private fun quickEval(firstNumber: String, operation: String, secondNumber: String): String {
        return when (operation) {
            context.getString(R.string.add) -> (firstNumber.toDouble() + secondNumber.toDouble()).toString()
            context.getString(R.string.subtract) -> (firstNumber.toDouble() - secondNumber.toDouble()).toString()
            context.getString(R.string.multiply) -> (firstNumber.toDouble() * secondNumber.toDouble()).toString()
            else -> (firstNumber.toDouble() / secondNumber.toDouble()).toString()
        }
    }
}