package com.cs4520.assignment3.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment3.Model.CalculatorModel
import com.cs4520.assignment3.utils.OperationType

class MVVMViewModel : ViewModel() {

    // MutableLiveData to hold the result
    private val _result = MutableLiveData<Double>()

    // Expose LiveData to observe changes in the result
    val result: LiveData<Double>
        get() = _result

    val model = CalculatorModel()

    // Function to perform calculation and update LiveData
    fun performCalculation(operationType: OperationType, input1: Double, input2: Double) {
       val resultValue = model.performCalculation(operationType, input1, input2);

        // Convert the result to String and update the LiveData
        _result.value = resultValue
    }
}