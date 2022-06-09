package com.example.mvvmpractice.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class RetrofitClient {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}