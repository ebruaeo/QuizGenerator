package com.example.quizgenerator.data

import com.example.quizgenerator.domain.QuestionModels

class Repository {

    private val remoteDataSource = RemoteDataSource()

    fun getQuestionList() = remoteDataSource.getQuestionList().map {
        QuestionModels(it.id, it.text, it.answer)
    }
}