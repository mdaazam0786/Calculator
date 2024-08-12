package com.example.calculator

sealed class CalculatorOperation(val operation: String) {
    object Divide : CalculatorOperation("/")
    object Multiply : CalculatorOperation("X")
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
}