package me.jhones.calculator.ui.models

import me.jhones.calculator.ui.Operation
import java.text.DecimalFormat

import kotlin.text.StringBuilder

class Calculator {

    private var currentNumber: StringBuilder = StringBuilder()

    private var typed : StringBuilder = StringBuilder()

    private var numberCache = 0f

    private var result: Float = 0f

    private var nextOperation: Operation? = null


    fun enter(number: Float) {
        verifyZero()
        typed.append(DecimalFormat("#").format(number))
        currentNumber.append(DecimalFormat("#").format(number))
    }

    fun getValue(): String {
        if (currentNumber.isEmpty()) currentNumber.append(0)
        if (typed.isEmpty()) typed.append(currentNumber)

        return typed.toString()
    }


    private fun verifyZero() {
        if (currentNumber.startsWith("0") && currentNumber.length == 1) {
            currentNumber.clear()
        }
        if (typed.startsWith("0") && typed.length == 1) {
           typed.clear()
        }
    }

    fun clear() {
        typed.clear()
        currentNumber.clear()
        result = 0f
        nextOperation = null
    }

    fun backspace() {
        if (currentNumber.isNotEmpty()) {
            currentNumber.setLength(currentNumber.length - 1)
            println(currentNumber)
        }
        if (typed.isNotEmpty()){
            typed.setLength(typed.length -1)

        }


    }

    fun addSum() {
        numberCache = result
        nextOperation = Operation.PLUS
        typed.append(" + ")
        currentNumber.clear()
    }

    fun addMultiplication() {
        numberCache = result
        nextOperation = Operation.MULTIPLICATION
        typed.append(" × ")
        currentNumber.clear()
    }

    fun addDivision() {
        numberCache = result
        nextOperation = Operation.DIVISION
        typed.append(" ÷ ")
        currentNumber.clear()
    }

    fun addSubtraction() {
        numberCache = result
        nextOperation = Operation.MINUS
        typed.append(" - ")
        currentNumber.clear()
    }

    fun getResult(): String {
        if (nextOperation != null) {
            val currentNum =  currentNumber.toString().toFloat()
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

      return formattedResult()
    }
    private fun formattedResult():String{
        val resultFormatted: String = if (result.toString().endsWith(".0")) {
            "=> ${DecimalFormat("#").format(result)}"
        }else{
            if (result < 0f && result.toString().length > 6f){
                "=> ${DecimalFormat("#0.0000#").format(result)}"
            }
            result.toString()
        }

        return resultFormatted
    }
    fun resultFloat(): Float{
        val resultFormatted: String = if (result.toString().endsWith(".0")) {
            DecimalFormat("#").format(result)
        }else{
            if (result < 0f && result.toString().length > 6f){
                DecimalFormat("#0.0000#").format(result)
            }
            result.toString()
        }

        return resultFormatted.toFloat()
    }
    fun equals(){

        numberCache = resultFloat()
        typed.clear()
        typed.append(result)
    }
}