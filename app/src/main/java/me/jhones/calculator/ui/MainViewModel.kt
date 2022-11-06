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
    fun enter(dot: String){
        calculator.enter(dot)
        updateValues()
    }

    private fun updateValues(){
        _enteredValues.value = calculator.getValue()
        _result.value = calculator.getResult()
    }

    fun addOperator(operation: Operation) {
        when (operation) {
            Operation.PLUS -> {
               calculator.addSum()
                updateValues()
            }
            Operation.MINUS-> {
               calculator.addSubtraction()
                updateValues()
            }
            Operation.MULTIPLICATION ->{
               calculator.addMultiplication()
                updateValues()
            }
            Operation.DIVISION ->{
                calculator.addDivision()
                updateValues()
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
        updateValues()
    }
fun equals(){
        calculator.equals()
}

}