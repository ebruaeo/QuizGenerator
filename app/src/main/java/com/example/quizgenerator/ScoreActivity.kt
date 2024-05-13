package com.example.quizgenerator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizgenerator.databinding.ActivityScoreScreenBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreScreenBinding

    companion object {
        const val SCORE_KEY = "scoreKey"
        const val QUESTION_COUNT_KEY = "QuestionCountKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScoreScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
    }

    private fun initUI() {
        val score = intent.getIntExtra(SCORE_KEY, -1)
        val questionCount = intent.getIntExtra(QUESTION_COUNT_KEY, -1)
        setScoreValues(score, questionCount)
        setUpSeekBar(questionCount)
        setStartAgainClickListener()
    }

    private fun setScoreValues(score: Int, questionCount: Int) {
        binding.run {
            scoreValue.text = score.toString()
            scoreExplanationText.text = "Quiz is finished. Your score is $score/$questionCount"
        }
    }

    private fun setUpSeekBar(questionCount: Int) {
        binding.seekBar.run {
            max = questionCount
            setOnTouchListener { view, motionEvent -> true }
            progress = max
        }
    }

    private fun setStartAgainClickListener() {
        binding.startAgainButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}

