package me.jhones.calculator.ui.models

import me.jhones.calculator.ui.Operation

class Expression(private var expression: String) {
    val value get() = calculateExpression()

    private val numbers = mutableListOf<Float>()
    private val operation = mutableListOf<Char>()

    private var result = 0f

    init {
        splitExpression()
    }

    private fun splitExpression(): Float {
        val currentNumber = StringBuilder()

        var index = 0
        for (char in expression) {

            val isOperation = (
                    char == Operation.PLUS.symbol ||
                            char == Operation.MULTIPLICATION.symbol ||
                            char == Operation.DIVISION.symbol ||
                            char == Operation.MINUS.symbol
                    )

            if (isOperation) {
                operation.add(char)
                try {
                    numbers.add(currentNumber.toString().toFloat())
                } catch (e: NumberFormatException) {
                    index++
                    continue
                }

                currentNumber.clear()
            } else if (char == ' ') {
                index++
                continue
            } else {
                currentNumber.append(char)

                if (index == expression.lastIndex) {
                    numbers.add(currentNumber.toString().toFloat())
                }
            }
            index++
        }

        return 0f
    }

    private fun addNextOperator(index: Int, op: Char) {


        when (op) {
            Operation.PLUS.symbol -> {
                try {
                    result += numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }
            }
            Operation.MINUS.symbol -> {
                try {
                    result -= numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }

            }
            Operation.MULTIPLICATION.symbol -> {
                try {
                    result *= numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }

            }
            Operation.DIVISION.symbol -> {
                try {
                    result /= numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }
            }

        }
    }

    private fun calculateExpression(): Float {
        for ((index, op) in operation.withIndex()) {
            if (index == 0) {
                result += numbers[0]
            }
            addNextOperator(index, op)
        }
        return result
    }

}
