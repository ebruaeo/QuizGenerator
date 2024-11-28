package com.example.quizgenerator.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.R
import com.example.quizgenerator.databinding.ActivityMainBinding
import com.example.quizgenerator.domain.Quiz

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var quiz = Quiz()


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
        quiz.fetchQuestionList()
        setUpSeekBar()
        getFirstQuestion()
        setOptionButtonsClickListener()
        startButtonClickListener()

    }

    private fun setOptionButtonsClickListener() {
        binding.run {
            optionOneButton.setOnClickListener {
                freezeButtons()
                checkAnswer(0, true)
                setNext()
            }
            optionTwoButton.setOnClickListener {
                freezeButtons()
                checkAnswer(1, false)
                setNext()
            }
        }
    }

    private fun setTrueButtonColor(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.green_button)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.green_button)
            }
        }
    }

    private fun setWrongButtonColor(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.red_button)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.red_button)
            }

        }
    }

    private fun resetButtonColor() {
        binding.run {
            optionOneButton.setBackgroundResource(R.drawable.my_button)
            optionTwoButton.setBackgroundResource(R.drawable.my_button)
        }
    }

    private fun setUpSeekBar() {
        binding.seekBar.max = quiz.getQuestionCount()
        binding.seekBar.setOnTouchListener { view, motionEvent -> true }

    }

    private fun getFirstQuestion() {
        binding.questionText.text = quiz.getCurrentQuestion().text
        binding.seekBar.progress++

    }

    private fun checkAnswer(
        selectedAnswerIndex: Int,
        answer: Boolean
    ) {
        // TODO
//        val answer = when (selectedAnswerIndex) {
//            0 -> true
//            1 -> false
//            else -> throw Exception("selectedAnswerIndex is wrong: $selectedAnswerIndex")
//        }

        if (quiz.isAnswerCorrect(answer)) {
            quiz.increaseScore()
            binding.score.text = quiz.scoreString()
            setTrueButtonColor(selectedAnswerIndex)
        } else {
            setWrongButtonColor(selectedAnswerIndex)
            val correctAnswerIndex = if (selectedAnswerIndex == 0) 1 else 0
            setTrueButtonColor(correctAnswerIndex)

        }
    }

    private fun freezeButtons() {
        binding.run {
            optionOneButton.isEnabled = false
            optionTwoButton.isEnabled = false
        }
    }

    private fun unfreezeButtons() {
        binding.run {
            optionOneButton.isEnabled = true
            optionTwoButton.isEnabled = true
        }
    }

    private fun displayNextQuestion() {
        binding.questionText.text = quiz.getNextQuestion().text
        resetButtonColor()
        unfreezeButtons()
        binding.seekBar.progress++
    }

    private fun setNext() {
        if (quiz.hasNextQuestion()) {
            binding.questionText.postDelayed({ displayNextQuestion() }, 1000)
        } else {
            binding.root.postDelayed({
                binding.questionText.text =
                    "Quiz is finished. Your score is ${quiz.scoreString()}/${quiz.questionCount()}."
                makeTFButtonsInvisible()
            }, 1000)

        }


    }

    private fun startButtonClickListener() {
        binding.startAgainButton.setOnClickListener {
            quiz.resetQuiz()
            resetUI()
            getFirstQuestion()
            makeTFButtonsVisible()
            setOptionButtonsClickListener()
        }
    }

    private fun makeTFButtonsInvisible() {
        binding.run {
            optionOneButton.visibility = View.INVISIBLE
            optionTwoButton.visibility = View.INVISIBLE
            startAgainButton.visibility = View.VISIBLE
        }
    }

    private fun makeTFButtonsVisible() {
        binding.run {
            optionOneButton.visibility = View.VISIBLE
            optionTwoButton.visibility = View.VISIBLE
            startAgainButton.visibility = View.INVISIBLE
        }
    }

    private fun resetUI(){
        unfreezeButtons()
        binding.seekBar.progress = 0
        binding.score.text=quiz.scoreString()
        resetButtonColor()
        setUpSeekBar()
    }
}