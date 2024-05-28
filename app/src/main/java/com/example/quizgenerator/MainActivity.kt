package com.example.quizgenerator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
        1- Modelleme
        2- Uygulama Akışı
            2.1- Uygulama açılışında yapılacak
                - Button click listener'lar setlenecek
                - İlk soru ekrana gelecek

            2.2- Buttona tıklanıldığında yapılacaklar
                2.2.1- Cevap kontrolü
                2.2.2- Doğruysa Yeşil renk
                2.2.3- Yanlışsa doğru cevap yeşil yanlış cevap kırmızı olacak
                2.2.4- 1 saniye sonra yeni soru gelecek
                2.2.5- Buttonların renkleri sıfırlanacak
                2.2.6- index'i 1 arttır
*/
    private lateinit var binding: ActivityMainBinding

    // Array, list
    private val questionList = listOf(
        Question("A slug's blood is green.", true),
        Question("You can lead a cow down stairs but not up stairs.", false),
        Question(
            "The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.",
            false
        ),
        Question("Approximately one quarter of human bones are in the feet.", true)
    )
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setButtonClickListeners()
        displayFirstQuestion()
    }

    private fun displayFirstQuestion() {
        binding.quizText.text = questionList.first().question
    }

    private fun setButtonClickListeners() {
        binding.trueButton.setOnClickListener {
            checkAnswer(true, binding.trueButton)
            displayNextQuestion()
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false, binding.falseButton)
            displayNextQuestion()
        }
    }

    private fun displayNextQuestion() {
        if (currentQuestionIndex != questionList.lastIndex) {
            binding.root.postDelayed({
                currentQuestionIndex++
                resetButtonColors()
                binding.quizText.text = questionList[currentQuestionIndex].question
            }, 1000L)
        }
    }

    private fun checkAnswer(givenAnswer: Boolean, clickedButton: AppCompatButton) {
        if (questionList[currentQuestionIndex].answer == givenAnswer) {
            makeGreen(clickedButton)
            score++
        } else {
            makeRed(clickedButton)
            if (clickedButton == binding.trueButton) {
                makeGreen(binding.falseButton)
            } else {
                makeGreen(binding.trueButton)
            }
        }
    }

    private fun makeGreen(button: AppCompatButton) {
        button.setBackgroundResource(R.drawable.bg_button_green)
    }

    private fun makeRed(button: AppCompatButton) {
        button.setBackgroundResource(R.drawable.bg_button_red)
    }

    private fun resetButtonColors() {
        binding.run {
            trueButton.setBackgroundResource(R.drawable.my_button)
            falseButton.setBackgroundResource(R.drawable.my_button)
        }
    }
}