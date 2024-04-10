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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            button0.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                }
                printText(getString(R.string.button_0))
            }
            button1.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_1))
            }
            button2.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_2))
            }
            button3.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_3))
            }
            button4.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_4))
            }
            button5.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_5))
            }
            button6.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_6))
            }
            button7.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_7))
            }
            button8.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_8))
            }
            button9.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null
                }
                printText(getString(R.string.button_9))
            }
            buttonDot.setOnClickListener {
                if (lastOperator == Operator.EQUAL) {
                    textViewScreen.text = "0"
                    lastOperator = null

                }
                printText(getString(R.string.button_dot))
            }
            buttonSum.setOnClickListener {
                printText(Operator.PLUS.str)
                checkResult(Operator.PLUS.str)
                lastOperator = Operator.PLUS
            }
            buttonMinus.setOnClickListener {
                printText(Operator.MINUS.str)
                checkResult(Operator.MINUS.str)
                lastOperator = Operator.MINUS
            }
            buttonMultiplication.setOnClickListener {
                printText(Operator.MULTIPLICATION.str)
                checkResult(Operator.MULTIPLICATION.str)
                lastOperator = Operator.MULTIPLICATION
            }

            buttonDivision.setOnClickListener {
                printText(Operator.DIVISION.str)
                checkResult(Operator.DIVISION.str)
                lastOperator = Operator.DIVISION
            }
            buttonC.setOnClickListener {
                textViewScreen.text = "0"
                number1 = 0.0
                number2 = 0.0
                result = 0.0
                lastOperator = null
            }
            buttonEqual.setOnClickListener {
                printText(Operator.EQUAL.str)
                checkResult("")
                lastOperator = Operator.EQUAL
            }
            buttonBack.setOnClickListener {
                val number = textViewScreen.text.toString()
                if (number.contains("+") ||
                    number.contains("-") ||
                    number.contains("*") ||
                    number.contains("/")
                ) {
                    lastOperator = null
                }
                if (number != "0") {
                    if (number.length == 1) {
                        textViewScreen.text = "0"
                    } else {
                        textViewScreen.text = number.substring(0, number.length - 1)
                    }
                }
            }
        }
    }

    private fun printText(text: String) {
        val screenText = binding.textViewScreen.text.toString()
        if (screenText == "0" && text != ".") {
            binding.textViewScreen.text = text
        } else if (screenText.contains("+") ||
            screenText.contains("-") ||
            screenText.contains("*") ||
            screenText.contains("/")
        ) {
            binding.textViewScreen.text = text
        } else {
            binding.textViewScreen.text = screenText + text
        }
    }

    private fun checkResult(operator: String) {
        try {
            with(binding.textViewScreen.text.toString()) {
                when (lastOperator) {
                    Operator.PLUS -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 + number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewScreen.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewScreen.text = "$result$operator"
                        }

                    }

                    Operator.MINUS -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 - number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewScreen.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewScreen.text = "$result$operator"
                        }
                    }

                    Operator.MULTIPLICATION -> {
                        number2 = substring(0, length - 1).toDouble()
                        result = number1 * number2
                        number1 = result
                        if (result % 1 == 0.0) {
                            binding.textViewScreen.text = "${result.toInt()}$operator"
                        } else {
                            binding.textViewScreen.text = "$result$operator"
                        }
                    }

                    Operator.DIVISION -> {
                        try {
                            number2 = substring(0, length - 1).toDouble()
                            result = number1 / number2
                            number1 = result
                            if (result % 1 == 0.0) {
                                binding.textViewScreen.text = "${result.toInt()}$operator"
                            } else {
                                binding.textViewScreen.text = "$result$operator"
                            }
                        } catch (e: Exception){
                            binding.textViewScreen.text = getString(R.string.cannot_divide_by_zero)
                            number1 = 0.0
                            number2 = 0.0
                            lastOperator = null
                        }


                    }

                    else -> {
                        if (this == "+" || this == "-" || this == "*" || this == "/" || this == "=") {
                            binding.textViewScreen.text = "0$operator"
                        } else {
                            number1 = substring(0, length - 1).toDouble()
                            if (number1 % 1 == 0.0) {
                                binding.textViewScreen.text = "${number1.toInt()}$operator"
                            } else {
                                binding.textViewScreen.text = "$number1$operator"
                            }
                        }
                    }
                }
            }

        }
        catch (e: Exception) {
            if (number1 % 1 == 0.0) {
                binding.textViewScreen.text = "${number1.toInt()}$operator"
            } else {
                binding.textViewScreen.text = "$number1$operator"
            }

        }

    }
}