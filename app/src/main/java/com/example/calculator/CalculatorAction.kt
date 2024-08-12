package com.example.calculator

sealed class CalculatorAction {
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()
    data class Number(val number: Int) : CalculatorAction()
    object Calculate : CalculatorAction()
    object Decimal : CalculatorAction()

}