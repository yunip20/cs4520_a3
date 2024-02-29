package com.cs4520.assignment3

import com.cs4520.assignment3.utils.OperationType

interface MVPContract {
    interface IMVPFragment {
        fun showResult(result: Double)
        // other methods related to UI interactions
    }

    interface IPresenter {
        fun calculate(operationType: OperationType, input1: Double, input2: Double)
        // other methods related to business logic
    }

    interface IModel {
        fun performCalculation(operationType: OperationType, input1: Double, input2: Double): Double
        // other methods related to data operations
    }
}