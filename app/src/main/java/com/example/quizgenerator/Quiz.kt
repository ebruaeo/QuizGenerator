package com.example.quizgenerator

class Quiz {

    private var score = 0
    private var currentQuestionIndex = 0

    private var questionList = listOf(
        Question(1, "A slug's blood is green.", true),
        Question(2, "You can lead a cow down stairs but not up stairs.", false),
//        Question(
//            3,
//            "The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.",
//            false
//        ),
//        Question(4, "Approximately one quarter of human bones are in the feet.", true)
    )

    fun isAnswerCorrect(givenAnswer: Boolean): Boolean {
        val currentQuestion = questionList[currentQuestionIndex]
        return currentQuestion.answer == givenAnswer
    }

    fun questionCount() = questionList.size

    fun getFirstQuestion() = questionList.first()

    fun increaseScore() = score++

    fun scoreString() = score.toString()

    fun getNextQuestion(): Question {
        currentQuestionIndex++
        return questionList[currentQuestionIndex]
    }

    fun hasNextQuestion() = currentQuestionIndex != questionList.lastIndex

    fun resetQuiz() {
        score = 0
        currentQuestionIndex = 0

    }
}

