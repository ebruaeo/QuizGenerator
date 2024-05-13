package com.example.quizgenerator

// Separation of Concerns
// Controller class
class Quiz {
    // Encapsulation
    private var score = 0
    private var currentQuestionIndex = 0
    private val questionList = arrayOf(
        Question(1, "A slug's blood is green.", true),
        Question(2, "Approximately one quarter of human bones are in the feet.", true),
        Question(
            3,
            "The total surface area of two human lungs is approximately 70 square metres.",
            true
        ),
        Question(
            4,
            "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.",
            true
        ),
    )

    fun isAnswerCorrect(givenAnswer: Boolean): Boolean {
        val currentQuestion = questionList[currentQuestionIndex]
        return currentQuestion.answer == givenAnswer
    }

    fun increaseScore() {
        score++
    }

    fun getScoreString() = score.toString()

    fun getQuestionCount() = questionList.size

    fun getScore() = score

    fun getNextQuestion(): Question {
        currentQuestionIndex++
        return questionList[currentQuestionIndex]
    }

    fun getCurrentQuestion() = questionList[currentQuestionIndex]

    fun hasNextQuestion() = currentQuestionIndex != questionList.lastIndex

    fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
    }
}