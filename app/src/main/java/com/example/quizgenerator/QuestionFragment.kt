package com.example.quizgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quizgenerator.databinding.FragmentQuestionBinding
import kotlin.math.atan

class QuestionFragment : Fragment() {
    private var quiz = Quiz()

    private lateinit var binding: FragmentQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("questionFragmentCreated")
        if (!::binding.isInitialized)
            binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSeekbar()
        getFirstQuestion()
        setButtonsClickListener()

    }


    private fun setButtonsClickListener() {
        binding.run {
            optionOneButton.setOnClickListener {
                freezeButtons()
                checkAnswer(0, true)
                setNextQuestion(it)

            }
            optionTwoButton.setOnClickListener {
                freezeButtons()
                checkAnswer(1, false)
                setNextQuestion(it)

            }
        }
    }

    private fun setSeekbar() {
        binding.run {
            seekBar.max = quiz.questionCount()
            seekBar.progress = 1
            seekBar.setOnTouchListener { view, motionEvent -> true }
        }
    }

    private fun getFirstQuestion() {
        binding.questionText.text = quiz.getFirstQuestion().question
    }

    private fun getNextQuestion() {
        binding.questionText.text = quiz.getNextQuestion().question
        resetButtonColors()
        unfreezeButtons()
        binding.seekBar.progress++
    }

    private fun setNextQuestion(it: View) {
        if (quiz.hasNextQuestion()) {
            binding.questionText.postDelayed({ getNextQuestion() }, 1000)
        } else {
            val action = QuestionFragmentDirections.actionQuestionFragmentToScoreFragment(
                quiz.scoreString(),
                quiz.questionCount().toString()
            )
            Navigation.findNavController(it).navigate(action)

        }
    }

    private fun setCorrectAnswerButtonColor(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.green_button)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.green_button)

            }
        }
    }

    private fun setWrongAnswerButtonColor(buttonIndex: Int) {
        binding.run {
            when (buttonIndex) {
                0 -> optionOneButton.setBackgroundResource(R.drawable.red_button)
                1 -> optionTwoButton.setBackgroundResource(R.drawable.red_button)

            }
        }
    }

    private fun resetButtonColors() {
        binding.run {
            optionOneButton.setBackgroundResource(R.drawable.my_button)
            optionTwoButton.setBackgroundResource(R.drawable.my_button)
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

    private fun checkAnswer(givenAnswerIndex: Int, answer: Boolean) {

        if (quiz.isAnswerCorrect(answer)) {
            quiz.increaseScore()
            binding.score.text = quiz.scoreString()
            setCorrectAnswerButtonColor(givenAnswerIndex)
        } else {
            setWrongAnswerButtonColor(givenAnswerIndex)
            val correctAnswerIndex = if (givenAnswerIndex == 0) 1 else 0
            setCorrectAnswerButtonColor(correctAnswerIndex)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    private fun resetUI (){
        quiz.resetQuiz()
        setSeekbar()
        getFirstQuestion()
        setButtonsClickListener()


    }
}