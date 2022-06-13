package com.example.mvvmpractice.repository

import com.example.mvvmpractice.model.TvModel
import com.example.mvvmpractice.model.network.RetrofitClient
import com.example.mvvmpractice.model.network.TvListServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class TvListRepo {

    val API_KEY = "3d816e403a33527408671d04f46dbf07"


    suspend fun fetchTvData(): TvModel? {
        val retrofit = RetrofitClient().getInstance()
        val request = retrofit.create(TvListServices::class.java)


        return withContext(Dispatchers.IO) {

            val async = async {

                request.getPopularTvShows(API_KEY)
            }

            val tvListResponse = async.await()

            tvListResponse
        }
    }
}