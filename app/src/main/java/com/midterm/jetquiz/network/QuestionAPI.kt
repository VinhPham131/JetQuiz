package com.midterm.jetquiz.network

import com.midterm.jetquiz.model.Question
import retrofit2.http.GET

interface QuestionAPI {
    @GET("itmmckernan/triviaJSON/master/world.json")
    suspend fun getAllQuestions() : Question
}