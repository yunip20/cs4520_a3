package com.cs4520.assignment3.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cs4520.assignment3.MVPContract
import com.cs4520.assignment3.utils.OperationType
import com.cs4520.assignment3.Presenter.Presenter
import com.cs4520.assignment3.databinding.FragmentMVPBinding
import com.cs4520.assignment3.utils.Utility

/**
 * A simple [Fragment] subclass.
 * Use the [MVPFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MVPFragment: Fragment(), MVPContract.IMVPFragment {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMVPBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val presenter: MVPContract.IPresenter = Presenter(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMVPBinding.inflate(inflater, container, false)
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
                OperationType.ADDITION -> presenter.calculate(OperationType.ADDITION, input1, input2)
                OperationType.SUBTRACTION -> presenter.calculate(OperationType.SUBTRACTION, input1, input2)
                OperationType.DIVISION -> presenter.calculate(OperationType.DIVISION, input1, input2)
                OperationType.MULTIPLICATION -> presenter.calculate(OperationType.MULTIPLICATION, input1, input2)
            }

            clearInputs()
        }

        // Set onClick listeners for buttons
        binding.btnAdd.setOnClickListener { onOperationButtonClick(OperationType.ADDITION) }
        binding.btnSub.setOnClickListener { onOperationButtonClick(OperationType.SUBTRACTION) }
        binding.btnDiv.setOnClickListener { onOperationButtonClick(OperationType.DIVISION) }
        binding.btnMul.setOnClickListener { onOperationButtonClick(OperationType.MULTIPLICATION) }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MVPFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MVPFragment().apply {
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showResult(result: Double) {
        binding.tvResult.text = String.format("Result: %f", result)
    }

}