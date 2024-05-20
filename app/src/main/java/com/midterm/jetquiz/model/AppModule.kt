package com.midterm.jetquiz.model

import com.midterm.jetquiz.network.QuestionAPI
import com.midterm.jetquiz.repository.QuestionRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionRepository(api : QuestionAPI) = QuestionRepository(api)

    @Singleton
    @Provides
    fun provideQuestionApi() : QuestionAPI{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionAPI::class.java)

    }
}