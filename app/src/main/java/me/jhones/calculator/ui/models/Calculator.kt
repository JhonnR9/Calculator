package me.jhones.calculator.ui.models

import me.jhones.calculator.ui.Operation
import java.text.DecimalFormat
import kotlin.NumberFormatException

import kotlin.text.StringBuilder

class Calculator {

    private var currentNumber: StringBuilder = StringBuilder()

    private var enteredValues: StringBuilder = StringBuilder()

    private var numbersCache = arrayListOf<String>()


    private var result: Float = 0f

    private var nextOperation: Operation? = null


    fun enter(number: Float) {
        verifyZero()
        enteredValues.append(DecimalFormat("#").format(number))
        currentNumber.append(DecimalFormat("#").format(number))
    }

    fun enter(dot: String) {
        if (!currentNumber.endsWith(".")) {
            enteredValues.append(dot)
            currentNumber.append(dot)
        }
    }

    fun getValue(): String {
        if (currentNumber.isEmpty()) currentNumber.append(0)
        if (enteredValues.isEmpty()) enteredValues.append(currentNumber)

        return enteredValues.toString()
    }


    private fun verifyZero() {
        if (currentNumber.startsWith("0") && currentNumber.length == 1) {
            currentNumber.clear()
        }
        if (enteredValues.startsWith("0") && enteredValues.length == 1) {
            enteredValues.clear()
        }
    }

    fun clear() {
        enteredValues.clear()
        currentNumber.clear()
        result = 0f
        nextOperation = null
    }

    fun backspace() {
        if (currentNumber.isNotEmpty()) {
            if (currentNumber.endsWith(" ")) {
                currentNumber.setLength(currentNumber.length - 3)
            } else {
                currentNumber.setLength(currentNumber.length - 1)
            }

        }
        if (enteredValues.isNotEmpty()) {
            if (enteredValues.endsWith(" ")) {
                enteredValues.setLength(enteredValues.length - 3)
            } else {
                enteredValues.setLength(enteredValues.length - 1)
            }

        }
        if (isOperation('+') || isOperation('×') ||
            isOperation('÷') || isOperation('-')
        ) {

            numbersCache.minusElement(getLastNumberCache())
        }


    }

    fun addSum() {
        numbersCache.add(getResult())
        nextOperation = Operation.PLUS
        checkSymbol("+")
        currentNumber.clear()
    }

    fun addMultiplication() {
        numbersCache.add(getResult())
        nextOperation = Operation.MULTIPLICATION
        checkSymbol("×")
        currentNumber.clear()
    }

    fun addDivision() {
        numbersCache.add(getResult())
        nextOperation = Operation.DIVISION
        checkSymbol("÷")
        currentNumber.clear()
    }

    fun addSubtraction() {
        numbersCache.add(getResult())
        nextOperation = Operation.MINUS
        checkSymbol("-")
        currentNumber.clear()
    }

    private fun checkSymbol(symbol: String) {
        if (!enteredValues.endsWith(symbol)) {
            enteredValues.append(symbol)
        }
    }

    private fun isOperation(symbol: Char): Boolean {
        return enteredValues.endsWith(symbol)

    }

    fun getResult(): String {
        if (nextOperation != null) {

            val currentNum = try {
                currentNumber.toString().toFloat()
            } catch (e: NumberFormatException) {
                0f
            }
            val numberCache = getLastNumberCache()
            result = when (nextOperation) {
                Operation.PLUS -> {
                    numberCache + currentNum
                }
                Operation.MINUS -> {
                    numberCache - currentNum
                }
                Operation.MULTIPLICATION -> {
                    numberCache * currentNum
                }

                else -> {
                    numberCache / currentNum
                }
            }

        } else {
            result = currentNumber.toString().toFloat()
        }

        return format(result)
    }

    private fun getLastNumberCache(): Float {
        val numberCache = if (numbersCache.isNotEmpty()) {
            numbersCache[numbersCache.lastIndex].toFloat()
        } else {
            0f
        }

        println(numberCache)

        return numberCache
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
        enteredValues.clear()
        enteredValues.append(getResult())
        numbersCache.add(format(result))
    }
}