package com.cs4520.assignment3.Presenter

import com.cs4520.assignment3.MVPContract
import com.cs4520.assignment3.Model.CalculatorModel
import com.cs4520.assignment3.utils.OperationType
import com.cs4520.assignment3.View.MVPFragment


class Presenter(private val view: MVPFragment) : MVPContract.IPresenter {

    private fun getModel(): CalculatorModel {
        return CalculatorModel()
    }

    override fun calculate(operationType: OperationType, input1: Double, input2: Double) {
        val result = getModel().performCalculation(operationType, input1, input2)

        // Now you would typically update the view with the result
        view.showResult(result)
    }
}
