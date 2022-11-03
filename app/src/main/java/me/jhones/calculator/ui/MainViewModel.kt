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
       updateValue()
    }
    private fun updateValue(){
        _enteredValues.value = calculator.getValue()
        _result.value = calculator.getResult()
    }

    fun addOperator(operation: Operation) {
        when (operation) {
            Operation.PLUS -> {
               calculator.addSum()
                updateValue()
            }
            Operation.MINUS-> {
               calculator.addSubtraction()
                updateValue()
            }
            Operation.MULTIPLICATION ->{
               calculator.addMultiplication()
                updateValue()
            }
            Operation.DIVISION ->{
                calculator.addDivision()
                updateValue()
            }
        }
    }

    fun clear() {
        calculator.clear()
        _enteredValues.value = calculator.getValue()
        _result.value = calculator.getResult()
    }

    fun backspace() {
        calculator.backspace()
        this._enteredValues.value = calculator.getValue()
    }


}