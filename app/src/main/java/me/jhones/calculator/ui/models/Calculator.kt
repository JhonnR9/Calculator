package me.jhones.calculator.ui.models

import me.jhones.calculator.ui.Operation
import java.text.DecimalFormat
import kotlin.NumberFormatException

import kotlin.text.StringBuilder

class Calculator {

    var enteredValues: StringBuilder = StringBuilder()

    private var numberCache: String = ""
    private var isAddOperator = false
    private var isEqualState = false


    fun enter(number: Float) {
        if (enteredValues.startsWith("0")&& !enteredValues.contains('.')){
            enteredValues.clear()
        }

        enteredValues.append(DecimalFormat( "#").format(number))
    }

    fun enter(dot: String) {
        if (!enteredValues.endsWith(".")) {
            enteredValues.append(dot)
        }
    }


    fun clear() {
        isAddOperator = false
        enteredValues.clear()
        enteredValues.append("0")
        isEqualState = false

    }

    fun backspace() {
        if (enteredValues.isNotEmpty()) {
            enteredValues.setLength(enteredValues.length - 1)
        }
        isAddOperator = (enteredValues.contains(Operation.PLUS.symbol)
                || enteredValues.contains(Operation.MINUS.symbol)
                || enteredValues.contains(Operation.MULTIPLICATION.symbol)
                || enteredValues.contains(Operation.DIVISION.symbol))


    }

    fun addOperator(operator: Char) {
        isAddOperator = true
        if (isEqualState) {
            enteredValues.clear()
            enteredValues.append(numberCache)
            isEqualState = false
        }
        enteredValues.append(" $operator ")
    }

    fun getResult(): String {
        if (!isAddOperator) {
            return enteredValues.toString()
        }
        return try {
            val expression = Expression(enteredValues.toString())
            format(expression.value)
        } catch (e: Exception){
            "Error"
        }

    }


    private fun format(number: Float): String {
        val formatted: String = if (number.toString().endsWith(".0")) {
            (" ${DecimalFormat("#").format(number)}")
        } else {
            if (number < 0f) {
                (" ${DecimalFormat("#0.0000#").format(number)}")
            } else {
                ( " $number")
            }

        }
        return formatted
    }

    fun equals() {
        numberCache = getResult()
        isEqualState = true
    }
}