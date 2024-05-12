package com.example.quizgenerator

import android.content.Intent
import android.os.Bundle
import android.view.View.OnTouchListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "A slug's blood is green.",
        "Approximately one quarter of human bones are in the feet.",
        "The total surface area of two human lungs is approximately 70 square metres.",
        "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.",
        /* "It is illegal to pee in the Ocean in Portugal.",
         "You can lead a cow down stairs but not up stairs.",
         "Google was originally called 'Backrub'.",
         "Buzz Aldrin's mother's maiden name was 'Moon'.",
         "The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.",
         "No piece of square dry paper can be folded in half more than 7 times.",
         "Chocolate affects a dog's heart and nervous system; a few ounces are enough to kill a small dog."*/
    )

    private val options = arrayOf(
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        /* arrayOf("True", "False"),
         arrayOf("True", "False"),
         arrayOf("True", "False"),
         arrayOf("True", "False"),
         arrayOf("True", "False"),
         arrayOf("True", "False"),
         arrayOf("True", "False"),*/
    )

    private val correctAnswers = arrayOf(
        0, 0, 0, 0, // 0, 1, 0, 0, 1, 1, 0
    )

    private var currentQuestionIndex = 0
    private var score = 0

    private var isAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpSeekBar()
        displayQuestions()

        //TODO**********************************************

        binding.optionOneButton.setOnClickListener {
            checkAnswer(0)
        }
        binding.optionTwoButton.setOnClickListener {
            checkAnswer(1)
        }


    }

    private fun setUpSeekBar() {
        binding.seekBar.run {
            // isEnabled = false
            max = questions.size
            setOnTouchListener { view, motionEvent -> true }
        }
    }


    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.optionOneButton.setBackgroundResource(R.drawable.green_background)
            1 -> binding.optionTwoButton.setBackgroundResource(R.drawable.green_background)
        }

    }

    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.optionOneButton.setBackgroundResource(R.drawable.red_background)
            1 -> binding.optionTwoButton.setBackgroundResource(R.drawable.red_background)
        }
    }

    private fun resetButtonColors() {
        binding.optionOneButton.setBackgroundResource(R.drawable.my_button)
        binding.optionTwoButton.setBackgroundResource(R.drawable.my_button)

    }

    private fun displayQuestions() {
        binding.quizText.text = questions[currentQuestionIndex]
        binding.optionOneButton.text = options[currentQuestionIndex][0]
        binding.optionTwoButton.text = options[currentQuestionIndex][1]
        resetButtonColors()
        binding.seekBar.progress += 1


    }


    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            binding.scoreValue.text = score.toString()
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.quizText.postDelayed({ displayQuestions() }, 1000)
        } else if (currentQuestionIndex == questions.size - 1) {
            val intent = Intent(this@MainActivity, ScoreActivity::class.java)
            intent.putExtra(ScoreActivity.SCORE_KEY, score)
            intent.putExtra(ScoreActivity.QUESTION_COUNT_KEY, questions.size)
            startActivity(intent)
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
    }

}



