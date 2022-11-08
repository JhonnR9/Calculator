package me.jhones.calculator.ui.models

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

            val isOperation = (char == '+' || char == '*' || char == '/' || char == '-')

            if (isOperation) {
                operation.add(char)
                numbers.add(currentNumber.toString().toFloat())
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
            '+' -> {
                try {
                    result += numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }
            }
            '-' -> {
                try {
                    result -= numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }

            }
            '*' -> {
                try {
                    result *= numbers[index + 1]
                } catch (e: IndexOutOfBoundsException) {

                }

            }
            '/' -> {
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
