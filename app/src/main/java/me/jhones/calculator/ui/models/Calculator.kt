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

    }

    fun backspace() {
        if (enteredValues.isNotEmpty()) {
            enteredValues.setLength(enteredValues.length - 1)
        }
        isAddOperator = (enteredValues.contains("+")
                || enteredValues.contains("-")
                || enteredValues.contains("*")
                || enteredValues.contains("/"))


    }

    fun addOperator(operator: String) {
        isAddOperator = true
        if (isEqualState) {
            enteredValues.clear()
            enteredValues.append(numberCache)
        }
        enteredValues.append(" $operator ")
    }

    fun getResult(): String {
        if (!isAddOperator) {
            return enteredValues.toString()
        }
        val expression = Expression(enteredValues.toString())
        return format(expression.value)
    }


    private fun format(number: Float): String {
        val formatted: String = if (number.toString().endsWith(".0")) {
            DecimalFormat("#").format(number)
        } else {
            if (number < 0f) {
                DecimalFormat("#0.0000#").format(number)
            } else {
                number.toString()
            }

        }
        return formatted
    }

    fun equals() {
        numberCache = getResult()
        isEqualState = true
    }
}