package com.zuzu.zuzanna_misztal_kalkulator_wtorek14

interface CalculatorInterface {
    var firstNumber: String
    var secondNumber: String
    var operation: String

    fun insertDigit(digit: Char)
    fun insertOperation(operation: String)
    fun clear()
    fun insertComa()
    fun evaluate()

}