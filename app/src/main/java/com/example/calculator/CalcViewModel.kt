package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class CalcViewModel : ViewModel() {

    private val state: MutableStateFlow<CalculatorState> = MutableStateFlow(CalculatorState())
    internal val viewState = state
        .map { state ->
            val num1 = state.num1.ifEmpty { "0" }
            val operator = state.operation?.operation.orEmpty()
            val num2 = state.num2

            ViewState("$num1 $operator $num2")
        }

    fun onAction(action : CalculatorAction){
        when(action){
            CalculatorAction.Calculate-> calculate()
            CalculatorAction.Delete-> delete()
            CalculatorAction.Clear-> Clear()
            CalculatorAction.Decimal-> decimal()
            is CalculatorAction.Number -> onNumber(action.number)
            is CalculatorAction.Operation -> onOperation(action.operation)
        }
    }

    private fun onOperation(operation: CalculatorOperation) {
        val currentState = state.value
        if (currentState.num1.isNotEmpty() && currentState.operation == null) {
            state.value = currentState.copy(operation = operation)
        }
    }

    private fun onNumber(number : Int){
        val currentState = state.value
        if(currentState.operation == null){
            state.value = currentState.copy(
                num1 = currentState.num1.plus(number)
            )
        }else{
            state.value = currentState.copy(
                num2 = currentState.num2.plus(number)
            )
        }
    }

    private fun delete(){
        val currentState = state.value
        if(currentState.operation == null && currentState.num1.isNotEmpty()){
            state.value = currentState.copy(num1 = currentState.num1.dropLast(1))
            return
        }
        else if(currentState.operation != null && currentState.num2.isNotEmpty()){
            state.value = currentState.copy(num2 = currentState.num2.dropLast(1))
            return
        }
        else{
            state.value  = currentState.copy(operation = null)
        }
    }

    private fun decimal(){
        val currentState = state.value
        if(currentState.operation == null && currentState.num1.isNotEmpty() && !currentState.num1.contains(".")){
            state.value  = currentState.copy(num1 = currentState.num1.plus("."))
            return
        }
        else if(currentState.operation != null && currentState.num2.isNotEmpty() && !currentState.num2.contains(".")){
            state.value = currentState.copy(num2 = currentState.num2.plus("."))
            return
        }
    }
    private fun Clear(){
        val currentState = state.value
        state.value = currentState.copy(num1 = "", num2 = "", operation = null)
    }

    private fun calculate(){
        val currentState = state.value

        if (currentState.num1.isNotEmpty() && currentState.num2.isNotEmpty() && currentState.operation != null) {
            val num1 = currentState.num1.toDouble()
            val num2 = currentState.num2.toDouble()

            val result = when (currentState.operation) {
                CalculatorOperation.Add -> num1 + num2
                CalculatorOperation.Subtract -> num1 - num2
                CalculatorOperation.Multiply -> num1 * num2
                CalculatorOperation.Divide -> num1 / num2
                null -> TODO()
            }

            state.value = currentState.copy(num1 = result.toString(), num2 = "", operation = null)
        }

    }

    internal class ViewState(val result: String)

}


