package com.example.quizgenerator.data


class RemoteDataSource {
    
    fun getQuestionList() = arrayOf(
        QuestionResponse(1, "A slug's blood is green.", true),
        QuestionResponse(2, "Approximately one quarter of human bones are in the feet.", true),
        QuestionResponse(
            3,
            "The total surface area of two human lungs is approximately 70 square metres.",
            true
        ),
        QuestionResponse(
            4,
            "In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.",
            true
        ),
    )
}