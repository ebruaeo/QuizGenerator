package com.example.quizgenerator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.databinding.ActivityMainBinding
import com.example.quizgenerator.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private val restTime = 1000L
    private val openScoreActivity = 1

    private lateinit var quiz: Quiz


    val activityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult>() { activityResult ->
            val resultCode = activityResult.resultCode
            val data = activityResult.data
            val intentData = data?.getStringExtra("a")
            if (resultCode == RESULT_OK) {
                restartQuiz()
            }

        }

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

      //  quiz = intent.getSerializableExtra("quiz") as? Quiz // data her yerden erişilebilir değilse bunu kullanıyoruz
        val position = intent.getIntExtra("position",-1)
        quiz = Data.quizList[position]


        setUpSeekBar()
        binding.displayFirstQuestion()
        setOptionButtonsClickListener()
    }


    private fun setOptionButtonsClickListener() {
        binding.run {
            optionOneButton.setOnClickListener {
                freezeButtons()
                checkAnswer(0)
                setNext()
            }
            optionTwoButton.setOnClickListener {
                freezeButtons()
                checkAnswer(1)
                setNext()
            }
        }
    }

    private fun setUpSeekBar() {
        binding.seekBar.run {
            // isEnabled = false
            max = quiz.getQuestionCount()
            setOnTouchListener { view, motionEvent -> true }
        }
    }

    private fun correctButtonColors(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.green_background)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.green_background)
            }
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.red_background)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.red_background)
            }
        }
    }

    private fun ActivityQuizBinding.resetButtonColors() {
        optionOneButton.setBackgroundResource(R.drawable.my_button)
        optionTwoButton.setBackgroundResource(R.drawable.my_button)
    }

    private fun ActivityQuizBinding.displayNextQuestion() {
        quizText.text = quiz.getNextQuestion().text
        resetButtonColors()
        seekBar.progress += 1
        unfreezeButtons()
    }

    private fun ActivityQuizBinding.displayFirstQuestion() {
        quizText.text = quiz.getCurrentQuestion().text
        seekBar.progress++
    }

    // TODO Use enum class
    private fun checkAnswer(selectedAnswerIndex: Int) {
        val answer = when (selectedAnswerIndex) {
            0 -> true
            1 -> false
            else -> throw Exception("selectedAnswerIndex is wrong: $selectedAnswerIndex")
        }

        if (quiz.isAnswerCorrect(answer)) {
            quiz.increaseScore()
            binding.scoreValue.text = quiz.getScoreString()
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            val correctAnswerIndex = if (selectedAnswerIndex == 0) 1 else 0
            correctButtonColors(correctAnswerIndex)
        }
    }

    private fun setNext() {
        if (quiz.hasNextQuestion()) {
            binding.quizText.postDelayed({ binding.displayNextQuestion() }, 1000)
        } else {
            val intent = Intent(this@QuizActivity, ScoreActivity::class.java)
            intent.putExtra(ScoreActivity.SCORE_KEY, quiz.getScore())
            intent.putExtra(ScoreActivity.QUESTION_COUNT_KEY, quiz.getQuestionCount())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activityLauncher.launch(intent)
            finish()
        }
    }

    private fun restartQuiz() {
        quiz.restartQuiz()
        binding.displayFirstQuestion()
        resetUI()
    }

    private fun resetUI() {
        binding.run {
            scoreValue.text = quiz.getScoreString()
            seekBar.progress = 1
            resetButtonColors()
            unfreezeButtons()
        }

    }

    private fun ActivityQuizBinding.freezeButtons() {
        optionOneButton.isEnabled = false
        optionTwoButton.isEnabled = false
    }

    private fun ActivityQuizBinding.unfreezeButtons() {
        optionOneButton.isEnabled = true
        optionTwoButton.isEnabled = true

    }
}


