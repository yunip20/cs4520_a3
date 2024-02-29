package com.cs4520.assignment3.utils
import android.content.Context
import android.widget.Toast

object Utility {
    object InputUtils {
        fun getInputs(context: Context, input1String: String, input2String: String): Array<Double> {
            var finalInput1 = 0.0
            var finalInput2 = 0.0

            if (input1String.isEmpty() && input2String.isEmpty()) {
                showToast(context, "Missing Input")
            } else {
                try {
                    finalInput1 = input1String.toDouble()
                    finalInput2 = input2String.toDouble()

                    if (finalInput2 == 0.0) {
                        showToast(context, "Cannot Divide by Zero")
                    } else if (finalInput1.isNaN() || finalInput1.isInfinite() || finalInput2.isNaN() || finalInput2.isInfinite()) {
                        showToast(context, "Invalid Input")
                    }
                } catch (e: NumberFormatException) {
                    showToast(context, "Invalid input")
                }
            }

            return arrayOf(finalInput1, finalInput2)
        }

        private fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
