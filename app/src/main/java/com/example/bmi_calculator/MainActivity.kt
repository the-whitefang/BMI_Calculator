package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
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
            if (validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2 = String.format("%.2f", bmi).toFloat()
                Result(bmi2)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{

        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Weight is Empty!! ",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Height is Empty!! ",Toast.LENGTH_LONG).show()
                return false
            }
            else-> {
                return  true
            }
        }
    }

    private fun Result(bmi2:Float){
        binding.txtIndex.text = bmi2.toString()
        binding.txtInfo.text = "( Normal range is 18.5 to 24.9 )"

        var resultText = ""
        var color = 0
        when{
            bmi2 < 18.50 ->{
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi2 in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi2 in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi2 > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        binding.txtResult.setTextColor(ContextCompat.getColor(this,color))
        binding.txtResult.text = resultText
    }
}