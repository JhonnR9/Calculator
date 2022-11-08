package me.jhones.calculator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.jhones.calculator.ui.models.Calculator

class MainViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _enteredValues: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val _result: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    val enteredValues: LiveData<String> = _enteredValues

    val result: LiveData<String> = _result

    fun enter(value: Float) {
        calculator.enter(value)
        updateValues()
    }

    fun enter(dot: String) {
        calculator.enter(dot)
        updateValues()
    }

    private fun updateValues() {
        _enteredValues.value = calculator.enteredValues.toString()
        _result.value = calculator.getResult()
    }

    fun addOperator(operation: Operation) {
        when (operation) {
            Operation.PLUS -> {
                calculator.addOperator("+")
                updateValues()
            }
            Operation.MINUS -> {
                calculator.addOperator("-")
                updateValues()
            }
            Operation.MULTIPLICATION -> {
                calculator.addOperator("*")
                updateValues()
            }
            Operation.DIVISION -> {
                calculator.addOperator("/")
                updateValues()
            }
        }
    }

    fun clear() {
        calculator.clear()
        updateValues()
    }

    fun backspace() {
        calculator.backspace()
        updateValues()
    }

    fun equals() {
        calculator.equals()
        updateValues()
    }

}