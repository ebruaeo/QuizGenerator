package com.example.quizgenerator.domain

import com.example.quizgenerator.data.Repository

class Quiz {
    private var score = 0
    private var currentQuestionIndex = 0
    private var questionList: List<QuestionModels> = listOf()
    private val repository = Repository()
    fun fetchQuestionList() {
        questionList = repository.getQuestionList()
    }

    fun getQuestionCount() = questionList.size
    fun getCurrentQuestion() = questionList[currentQuestionIndex]

    fun isAnswerCorrect(givenAnswer: Boolean): Boolean {
        val currentQuestion = questionList[currentQuestionIndex]
        return currentQuestion.answer == givenAnswer
    }

    fun increaseScore() {
        score++
    }

    fun scoreString() = score.toString()

    fun questionCount() = questionList.size

    fun hasNextQuestion() = currentQuestionIndex != questionList.lastIndex

    fun getNextQuestion(): QuestionModels {
        currentQuestionIndex++
        return questionList[currentQuestionIndex]
    }

    fun resetQuiz() {
        score = 0
        currentQuestionIndex = 0
    }


}