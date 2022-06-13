package com.example.mvvmpractice.repository

import com.example.mvvmpractice.model.HeadingModel
import com.example.mvvmpractice.model.LatestTvShowDetailsModel
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.model.TvModel
import com.example.mvvmpractice.model.network.RetrofitClient
import com.example.mvvmpractice.model.network.TvListServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoreTvShowsRepo {

    private val API_KEY = "3d816e403a33527408671d04f46dbf07"
    private val retrofit = RetrofitClient().getInstance()
    val request = retrofit.create(TvListServices::class.java)

    suspend fun getAllTvData(tvId: String): MutableList<Any> {

        return withContext(Dispatchers.IO)
        {
            val currentTvShow = async { request.getTvShowDetails(key = API_KEY, tvId = tvId) }
            val otherPopularTvShows = async { request.getPopularTvShows(key = API_KEY) }
            val latestTvShows = async { request.getLatestTvShows(key = API_KEY) }

            return@withContext getFinalTvShowList(
                currentTvShow.await(),
                otherPopularTvShows.await(),
                latestTvShows.await()
            )
        }
    }

    private fun getFinalTvShowList(
        currentTvShow: Result,
        otherPopularTvShows: TvModel,
        latestTvShows: LatestTvShowDetailsModel
    ): MutableList<Any> {

        val itemList = mutableListOf<Any>()

        if (itemList.size == 0) {
            itemList.add(currentTvShow)
        }

        if (itemList.size == 1) {
            itemList.add(getHeadingModel("OTHER POPULAR SHOWS"))
        }

        if (itemList.size == 2) {

            // Filtering out list of shows which are different from currently selected Tv show
            val filteredList = otherPopularTvShows.results.filter { it.id != currentTvShow.id }


            itemList.addAll(filteredList.subList(0, 2))

        }

        if (itemList.size == 4) {
            itemList.add(getHeadingModel("LATEST MOVIES"))
        }

        if (itemList.size == 5) {
            itemList.add(latestTvShows)
        }

        return itemList

    }

    private fun getHeadingModel(headingText: String): HeadingModel {
        return HeadingModel(headingText)
    }

}