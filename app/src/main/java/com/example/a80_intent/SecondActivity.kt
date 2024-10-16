package com.example.a80_intent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var oneOperandET: EditText
    private lateinit var twoOperandET: EditText

    private lateinit var sumBTN: Button
    private lateinit var difBTN: Button
    private lateinit var multBTN: Button
    private lateinit var divBTN: Button
    private lateinit var transferDataBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        oneOperandET = findViewById(R.id.oneOperandET)
        twoOperandET = findViewById(R.id.twoOperandET)

        sumBTN = findViewById(R.id.sumBTN)
        difBTN = findViewById(R.id.difBTN)
        multBTN = findViewById(R.id.multBTN)
        divBTN = findViewById(R.id.divBTN)
        transferDataBTN = findViewById(R.id.transferDataBTN)

        sumBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)
        multBTN.setOnClickListener(this)
        divBTN.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        var result = 0.0

        if (oneOperandET.text.isEmpty() || !oneOperandET.text.isDigitsOnly() ||
            twoOperandET.text.isEmpty() || !twoOperandET.text.isDigitsOnly()
        ) {
            return
        }

        val first = oneOperandET.text.toString().toDouble()
        val second = twoOperandET.text.toString().toDouble()

        result = when (v.id) {
            R.id.sumBTN -> Operation(first, second).sum()
            R.id.difBTN -> Operation(first, second).dif()
            R.id.multBTN -> Operation(first, second).mult()
            R.id.divBTN -> {
                if (second == 0.0) {
                    Toast.makeText(this, "Ошибка! Делить на ноль нельзя!", Toast.LENGTH_LONG).show()
                    return
                }
                Operation(first, second).div()
            }

            else -> 0.0
        }
        transferDataBTN.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", result.toString())
            startActivity(intent)
        }
    }

}