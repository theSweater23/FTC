package com.example.ftc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class ConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val from: EditText = findViewById(R.id.rubles)
        var rubles = 0.0
        from.addTextChangedListener {
            if(from.text.isNotEmpty())
                rubles = from.text.toString().toDouble()
        }

        val to: TextView = findViewById(R.id.currencyTo)
        val code = intent.getStringExtra("CHAR_CODE")
        to.text = code

        val currencyToValue = intent.getStringExtra("VALUE")?.toDouble()

        val nominal = intent.getStringExtra("NOMINAL")?.toInt()

        val converted: TextView = findViewById(R.id.converted)

        findViewById<Button>(R.id.convert).setOnClickListener {
            converted.text = Convert(rubles, currencyToValue!!, nominal!!).toString()
        }
    }

    private fun Convert(rubles: Double, toValue: Double, nominal: Int) : Float = (rubles/(toValue/nominal)).toFloat()
}