package com.example.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var btnAC: Button
    private lateinit var btnPlusMinus: Button
    private lateinit var btnPercent: Button
    private lateinit var btnDivision: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnMultiply: Button
    private lateinit var btn4: Button
    private lateinit var btn5 : Button
    private lateinit var btn6 : Button
    private lateinit var btnSubtract : Button
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button
    private lateinit var btnSum : Button
    private lateinit var btn0 : Button
    private lateinit var btnComma : Button
    private lateinit var btnEval : Button
    private lateinit var prevNums : TextView
    private lateinit var currentNum : TextView
    private var selectedAction by Delegates.notNull<String>()

    private var prevVal : Int = 0
    private var currentVal : Int = 0
    private lateinit var action : String

    private var num1 : Double = 0.0
    private var num2 : Double = 0.0
    private var accumulate : Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        action = ""

        //Buttons
        btnAC = findViewById(R.id.btn_ac)
        btnPlusMinus = findViewById(R.id.btn_positive_negative)
        btnPercent = findViewById(R.id.btn_percent)
        btnDivision = findViewById(R.id.btn_division)
        btnSum = findViewById(R.id.btn_sum)
        btnSubtract = findViewById(R.id.btn_subtraction)
        btnComma = findViewById(R.id.btn_comma)
        btnMultiply = findViewById(R.id.btn_multiplication)
        btnEval = findViewById(R.id.btn_equals)
        btn7 = findViewById(R.id.btn_7)
        btn8 = findViewById(R.id.btn_8)
        btn9 = findViewById(R.id.btn_9)
        btn4 = findViewById(R.id.btn_4)
        btn5 = findViewById(R.id.btn_5)
        btn6 = findViewById(R.id.btn_6)
        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)
        btn3 = findViewById(R.id.btn_3)
        btn0 = findViewById(R.id.btn_0)

        selectedAction = ""

        //TextViews
        currentNum = findViewById(R.id.current_num)

        //listeners
        btnAC.setOnClickListener(this)
        btnPlusMinus.setOnClickListener(this)
        btnPercent.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnSum.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnComma.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnEval.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn0.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        try {
            var result = accumulate

            when(v?.id) {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_comma-> {
                    var digit = (v as Button).text.toString()
                    if(action == "=") {
                        resetValues()
                    }
                    if(digit == "." && currentNum.text.toString().contains('.')) {
                        return
                    }
                    if(currentNum.text.toString() == "0") {
                        if(digit == "." ){
                            currentNum.text = "${currentNum.text}$digit"
                        }
                        else {
                            currentNum.text = "$digit"
                        }
                    }
                    else {
                        currentNum.text = "${currentNum.text}$digit"
                    }
                }
                R.id.btn_ac -> {
                    resetValues()
                }
                R.id.btn_sum -> {
                    action = "+"
                    setNum()
                }
                R.id.btn_subtraction -> {
                    action = "-"
                    setNum()
                }
                R.id.btn_division -> {
                    action = "/"
                    setNum()
                }
                R.id.btn_multiplication -> {
                    action = "*"
                    setNum()
                }
                R.id.btn_percent -> {
                    action = "%"
                    currentNum.text = "${currentNum.text.toString().toDouble() / 100}"
                }
                R.id.btn_comma -> {
                    action = "."
                }
                R.id.btn_positive_negative -> {
                    action = "+/-"
                    if(currentNum.text.toString().toDouble() > 0) {
                        currentNum.text = "-" + currentNum.text
                        num1 = currentNum.text.toString().toDouble()
                    }
                    else {
                        currentNum.text = currentNum.text.drop(1)
                        num1 = currentNum.text.toString().toDouble()
                    }
                }
                R.id.btn_equals -> {
                    if(action != ""){
                        when(action) {
                            "+" -> {
                                num2 = currentNum.text.toString().toDouble()
                                accumulate = num1 + num2
                                currentNum.text = "${num1 + num2}"
                                num1 = accumulate
                            }
                            "-" -> {
                                num2 = currentNum.text.toString().toDouble()
                                accumulate = num1 - num2
                                currentNum.text = "${num1 - num2}"
                                num1 = accumulate
                            }
                            "/" -> {
                                num2 = currentNum.text.toString().toDouble()
                                accumulate = num1 / num2
                                currentNum.text = "${num1 / num2}"
                                num1 = accumulate
                            }
                            "*" -> {
                                num2 = currentNum.text.toString().toDouble()
                                accumulate = num1 * num2
                                currentNum.text = "${num1 * num2}"
                                num1 = accumulate
                            }
                        }
                        action = "="
                    }
                }
            }
        }
        catch (e: NumberFormatException) {
            println("Error")
        }
    }
    private fun setNum() {
        if(num1 == 0.0) {
            num1 = currentNum.text.toString().toDouble()
        }
        else if(num1 != 0.0 && num2 == 0.0) {
            num2 = currentNum.text.toString().toDouble()
        }
        currentNum.text = "0"
    }

    private fun resetValues() {
        accumulate = 0.0
        prevVal = 0
        currentVal = 0
        action = ""
        currentNum.text = "0"
        num2 = 0.0
        num1 = 0.0
    }

}