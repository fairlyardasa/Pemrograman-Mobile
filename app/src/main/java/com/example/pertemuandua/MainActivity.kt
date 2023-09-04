package com.example.pertemuandua
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pertemuandua.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var txtResult: TextView
    private lateinit var txtFirstNum: TextView
    private lateinit var txtOperator: TextView
    private lateinit var txtSecondNum: TextView
    private var notFirstNum:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        txtResult = findViewById(R.id.txt_result)
        txtFirstNum = findViewById(R.id.txt_firstNum)
        txtOperator = findViewById(R.id.txt_opr)
        txtSecondNum = findViewById(R.id.txt_secondNum)



    }

    fun inputNum(view: View) {
        val button = view as Button

        if (!notFirstNum) {
            if (txtFirstNum.text != "0") {
                txtFirstNum.text = txtFirstNum.text.toString() + button.text.toString()
            }
            else {
                txtFirstNum.text = button.text.toString()
            }
        } else {
            if (txtSecondNum.text != "0") {
                txtSecondNum.text = txtSecondNum.text.toString() + button.text.toString()
            }
            else {
                txtSecondNum.text = button.text.toString()
            }

        }

    }

    fun operatorProcess(view: View) {
        val button = view as Button

        txtOperator.text = button.text.toString()
        notFirstNum = true
    }

    fun operatorEqualTo(view: View) {
        val button = view as Button
        val firstNum = txtFirstNum.text.toString().toInt()
        val secondNum = txtSecondNum.text.toString().toInt()

        when (txtOperator.text.toString()) {
            "+" -> {
                txtResult.text = (firstNum + secondNum).toString()
            }
            "-" -> {
                txtResult.text = (firstNum - secondNum).toString()
            }
            "*" -> {
                txtResult.text = (firstNum * secondNum).toString()
            }
            "/" -> {
                if (secondNum == 0) {
                    txtResult.text = "undefined"

                } else txtResult.text = (firstNum / secondNum).toString()
            }
        }
    }

    fun operatorClear(view: View) {
        val button = view as Button

        txtResult.text = "0"
        txtFirstNum.text = "0"
        txtOperator.text = "..."
        txtSecondNum.text = "0"
        notFirstNum = false

    }







}