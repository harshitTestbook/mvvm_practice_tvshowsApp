package com.example.mvvmpractice.model.network

import com.example.mvvmpractice.model.LatestTvShowDetailsModel
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.model.TvModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvListServices {

    @GET("popular")
    suspend fun getPopularTvShows(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TvModel

    @GET("latest")
    suspend fun getLatestTvShows(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
    ): LatestTvShowDetailsModel

    @GET("{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvId: String,
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
    ): Result
}