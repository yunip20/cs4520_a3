package com.cs4520.assignment3.Model

import com.cs4520.assignment3.MVPContract
import com.cs4520.assignment3.utils.OperationType

class CalculatorModel : MVPContract.IModel {
    override fun performCalculation(operationType: OperationType, input1: Double, input2: Double): Double {
        val result = when (operationType) {
            OperationType.ADDITION -> input1 + input2
            OperationType.SUBTRACTION -> input1 - input2
            OperationType.MULTIPLICATION -> input1*input2
            OperationType.DIVISION -> {
                if (input2 != 0.0) {
                    input1 / input2
                } else {
                    // Handle division by zero error, you may return a special value or throw an exception
                    // For now, let's return Double.NaN (Not a Number) as an example
                    Double.NaN
                }
            }
        }
        return result
    }
}