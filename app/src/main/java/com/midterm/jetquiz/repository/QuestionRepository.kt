package com.midterm.jetquiz.repository

import com.midterm.jetquiz.data.DataOrException
import com.midterm.jetquiz.model.QuestionItem
import com.midterm.jetquiz.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api : QuestionAPI){
    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions() :
            DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if(dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false

        }catch (exception : Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }

}