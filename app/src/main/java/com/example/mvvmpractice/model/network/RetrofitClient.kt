package com.example.mvvmpractice.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun getInstance(): Retrofit {

        val BASE_URL = "https://api.themoviedb.org/3/tv/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}