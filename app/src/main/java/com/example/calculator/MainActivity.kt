package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var strNumber = StringBuilder()
    private lateinit var helper: String
    private var helper2 = true
    private var operator = Operator.NONE
    private var isOperatorClicked = false
    private var operand1 = 0.0
    private var dot_helper = true
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButtons: Array<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberButtons = arrayOf(calculator0, calculator1, calculator2, calculator3, calculator4, calculator5, calculator6, calculator7, calculator8, calculator9, calculatorDot)
        operatorButtons = arrayOf(calculatorDivide, calculatorMultiply, calculatorPlus, calculatorMinus)

        for (i in numberButtons) {
            i.setOnClickListener { numberButtonClick(i) }
        }
        for (i in operatorButtons) {
            i.setOnClickListener { operatorButtonClick(i) }
        }

        calculatorClear.setOnClickListener { clearButtonCLick() }
        calculatorEqual.setOnClickListener { equalButtonClick() }
        calculatorPercent.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                calculatorInput.text = strNumber
                var Percents = calculatorInput.text.toString().toDouble() / 100
                calculatorInput.setText(Percents.toString())
                strNumber.clear()
                strNumber.append(calculatorInput.text)
            }
        })
        calculatorPlusMinus.setOnClickListener {
            if (helper2) {
                helper = strNumber.toString()
                strNumber.clear()
                strNumber.append("-$helper")
                calculatorInput.text = strNumber
                helper2 = false
            } else {
            }
        }
        calculatorDot.setOnClickListener {
            if (dot_helper) {
                strNumber.append(".")
                calculatorInput.text = strNumber
                dot_helper = false
            } else {
            }
        }
    }

    private fun Display() {
        try {
            val textValue = strNumber.toString().toDouble()
            calculatorInput.text = textValue.toString()
        } catch (ex: Exception) {
            strNumber.clear()
            calculatorInput.text = "Error"
        }
        calculatorInput.text = strNumber
    }

    private fun equalButtonClick() {
        var operand2 = strNumber.toString().toDouble()
        var result = 0.0
        when (operator) {
            Operator.ADD -> {
                result = operand1 + operand2
                dot_helper = true
                helper2 = true

            }
            Operator.SUB -> {
                result = operand1 - operand2
                dot_helper = true
                helper2 = true
            }
            Operator.MUL -> {
                result = operand1 * operand2
                dot_helper = true
                helper2 = true
            }
            Operator.DIV -> {
                result = operand1 / operand2
                dot_helper = true
                helper2 = true
            }
            else -> 0
        }
        if (result == 0.0) {
            strNumber.clear()
            strNumber.append("0")
            calculatorInput.text = strNumber
        }
        strNumber.clear()
        strNumber.append(result.toString())
        calculatorInput.text = strNumber
        helper2 = true
        dot_helper = true
    }

    private fun clearButtonCLick() {
        strNumber.clear()
        calculatorInput.text = "0"
        dot_helper = true
        helper2 = true
    }

    private fun operatorButtonClick(btn: Button) {
        if (btn.text == "+") {
            operator = Operator.ADD
            dot_helper = true
        } else if (btn.text == "-") {
            operator = Operator.SUB
            dot_helper = true
        } else if (btn.text == "×") {
            operator = Operator.MUL
            dot_helper = true
        } else if (btn.text == "÷") {
            operator = Operator.DIV
            dot_helper = true
        } else {
            btn.text = "ERROR"
        }
        isOperatorClicked = true
    }

    private fun numberButtonClick(btn: Button) {
        if (isOperatorClicked) {
            operand1 = strNumber.toString().toDouble()
            strNumber.clear()
            isOperatorClicked = false
        }
        strNumber.append(btn.text)
        Display()
    }

    enum class Operator { MUL, DIV, ADD, SUB, NONE }
}
