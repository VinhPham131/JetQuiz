package com.midterm.jetquiz.viewmodel

import android.provider.ContactsContract.Data
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.jetquiz.data.DataOrException
import com.midterm.jetquiz.model.QuestionItem
import com.midterm.jetquiz.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel
    @Inject constructor(private val repository: QuestionRepository) : ViewModel() {

        val data : MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>>
        = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuestions()
    }
    fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()

            if(data.value.toString().isNotEmpty())
                data.value.loading = false
        }
    }

    fun getTotalQuestionsCount(): Int {
        return data.value.data?.toMutableList()?.size!!

    }
}