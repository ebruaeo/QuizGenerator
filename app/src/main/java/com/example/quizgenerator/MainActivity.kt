package com.example.quizgenerator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setCorrectAnswerButtonColor (){
        binding.run {
            optionOneButton.setBackgroundResource(R.drawable.green_button)
            optionTwoButton.setBackgroundResource(R.drawable.green_button)
        }
    }

    private fun setWrongAnswerButtonColor (){
        binding.run {
            optionOneButton.setBackgroundResource(R.drawable.red_button)
            optionTwoButton.setBackgroundResource(R.drawable.red_button)
        }
    }
    private fun resetButtonColors (){
        binding.run {
            optionOneButton.setBackgroundResource(R.drawable.my_button)
            optionTwoButton.setBackgroundResource(R.drawable.my_button)
        }
    }





}