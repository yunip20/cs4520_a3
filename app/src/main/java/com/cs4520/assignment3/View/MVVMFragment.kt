package com.cs4520.assignment3.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cs4520.assignment3.ViewModel.MVVMViewModel
import com.cs4520.assignment3.utils.OperationType
import com.cs4520.assignment3.databinding.FragmentMVVMBinding
import com.cs4520.assignment3.utils.Utility

class MVVMFragment : Fragment() {

    companion object {
        fun newInstance() = MVVMFragment()
    }

    private lateinit var viewModel: MVVMViewModel
    private var _binding: FragmentMVVMBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMVVMBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun clearInputs() {
        binding.evNumInput1.text.clear()
        binding.evNumInput2.text.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var input1 = 0.0
        var input2 = 0.0

        // Common click listener function
        fun onOperationButtonClick(operationType: OperationType) {
            val input1String = binding.evNumInput1.text.toString()
            val input2String = binding.evNumInput2.text.toString()
            val context = requireContext()
            val inputs = Utility.InputUtils.getInputs(context, input1String, input2String)
            input1 = inputs[0]
            input2 = inputs[1]
            when (operationType) {
                OperationType.ADDITION -> viewModel.performCalculation(OperationType.ADDITION, input1, input2)
                OperationType.SUBTRACTION -> viewModel.performCalculation(OperationType.SUBTRACTION, input1, input2)
                OperationType.DIVISION -> viewModel.performCalculation(OperationType.DIVISION, input1, input2)
                OperationType.MULTIPLICATION -> viewModel.performCalculation(OperationType.MULTIPLICATION, input1, input2)
            }

            viewModel.result.observe(viewLifecycleOwner, Observer<Double> { result ->
                // Update UI with the result (e.g., display it in a TextView)
                binding.tvResult.text = String.format("Result: %f", result)
            })
            clearInputs()
        }

        // Set onClick listeners for buttons
        binding.btnAdd.setOnClickListener { onOperationButtonClick(OperationType.ADDITION) }
        binding.btnSub.setOnClickListener { onOperationButtonClick(OperationType.SUBTRACTION) }
        binding.btnDiv.setOnClickListener { onOperationButtonClick(OperationType.DIVISION) }
        binding.btnMul.setOnClickListener { onOperationButtonClick(OperationType.MULTIPLICATION) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MVVMViewModel::class.java)
        // TODO: Use the ViewModel
    }

}