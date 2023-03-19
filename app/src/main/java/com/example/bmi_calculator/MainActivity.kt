package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.bmi_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnCalc.setOnClickListener {
            val weight = binding.edtWeight.text.toString()
            val height = binding.edtHeight.text.toString()

            val bmi = weight.toFloat()/ ((height.toFloat()/100)* (height.toFloat()/100))
            val bmi2 = String.format("%.2f",bmi).toFloat()
            Result(bmi2)
        }
    }

    private fun Result(bmi2:Float){
        binding.txtIndex.text = bmi2.toString()
        binding.txtInfo.text = "( Normal range is 18.5 to 24.9 )"
    }
}