package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var number1 = 0.0
    private var number2 = 0.0
    private var result = 0.0
    private var lastOperator: Operator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main).apply {
            activity = this@MainActivity
        }
    }

    fun onNumberClick(value: String) {
        if (lastOperator == Operator.EQUAL) {
            binding.textViewResult.text = "0"
            lastOperator = null
        }
        printText(value)
    }

    fun onOperatorClick(value: Operator) {
        printText(value.str)
        checkResult(value.str)
        lastOperator = value
    }

    fun onButtonClearClick() {
        binding.textViewResult.text = "0"
        number1 = 0.0
        number2 = 0.0
        result = 0.0
        lastOperator = null
    }

    fun onButtonEqualClick() {
        printText(Operator.EQUAL.str)
        checkResult("")
        lastOperator = Operator.EQUAL
    }

    fun onButtonBackClick() {
        val number = binding.textViewResult.text.toString()
        if (number.contains("+") ||
            number.contains("-") ||
            number.contains("*") ||
            number.contains("/")
        ) {
            lastOperator = null
        }
        if (number != "0") {
            if (number.length == 1) {
                binding.textViewResult.text = "0"
            } else {
                binding.textViewResult.text = number.substring(0, number.length - 1)
            }
        }
    }


    private fun printText(text: String) {
        val screenText = binding.textViewResult.text.toString()
        if (screenText == "0" && text != "." && lastOperator == null) {
            binding.textViewResult.text = text
        } else if (screenText.contains("+") ||
            screenText.contains("-") ||
            screenText.contains("*") ||
            screenText.contains("/")
        ) {
            binding.textViewResult.text = text
        } else {
            binding.textViewResult.text = screenText + text
        }
    }

    private fun checkResult(operator: String) {
        try {
            with(binding.textViewResult.text.toString()) {
                when (lastOperator) {
                    Operator.PLUS -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 + number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewResult.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewResult.text = "$result$operator"
                        }

                    }

                    Operator.MINUS -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 - number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewResult.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewResult.text = "$result$operator"
                        }
                    }

                    Operator.MULTIPLICATION -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 * number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewResult.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewResult.text = "$result$operator"
                        }
                    }

                    Operator.DIVISION -> {
                        try {
                            number2 = substring(0, length - 1).toDouble()
                            result = number1 / number2
                            number1 = result
                            if (result % 1 == 0.0) {
                                binding.textViewResult.text = "${result.toInt()}$operator"
                            } else {
                                binding.textViewResult.text = "$result$operator"
                            }
                        } catch (e: Exception){
                            binding.textViewResult.text = getString(R.string.cannot_divide_by_zero)
                            number1 = 0.0
                            number2 = 0.0
                            lastOperator = null
                        }


                    }

                    else -> {
                        if (this == "+" || this == "-" || this == "*" || this == "/" || this == "=") {
                            binding.textViewResult.text = "0$operator"
                        } else {
                            number1 = substring(0, length - 1).toDouble()
                            if (number1 % 1 == 0.0) {
                                binding.textViewResult.text = "${number1.toInt()}$operator"
                            } else {
                                binding.textViewResult.text = "$number1$operator"
                            }
                        }
                    }
                }
            }

        }
        catch (e: Exception) {
            if (number1 % 1 == 0.0) {
                binding.textViewResult.text = "${number1.toInt()}$operator"
            } else {
                binding.textViewResult.text = "$number1$operator"
            }

        }

    }
}