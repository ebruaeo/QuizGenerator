package com.example.quizgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizgenerator.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {


    private lateinit var binding: FragmentScoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val scoreString = ScoreFragmentArgs.fromBundle(it).score
            binding.scoreValue.text = scoreString
            var questionCount = ScoreFragmentArgs.fromBundle(it).questionCount
            binding.scoreExplanationText.text =
                "Quiz finished. Your score is $scoreString/$questionCount."

        }
        setStartAgainButton()
        setSeekbar()

    }

    private fun setStartAgainButton() {
        binding.startAgainButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setSeekbar() {
        binding.run {
            seekBar.setOnTouchListener { view, motionEvent -> true }
        }
    }

}