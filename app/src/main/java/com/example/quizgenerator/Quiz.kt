    package com.example.quizgenerator

    import java.io.Serializable

    // Separation of Concerns
    // Controller class
    class Quiz (val name: String,val questionList : List<Question>): Serializable{
        // Encapsulation
        private var score = 0
        private var currentQuestionIndex = 0



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