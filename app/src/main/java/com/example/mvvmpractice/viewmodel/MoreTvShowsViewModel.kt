package com.example.mvvmpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.repository.MoreTvShowsRepo
import kotlinx.coroutines.launch

class MoreTvShowsViewModel(private val tvId: String) : ViewModel(), ViewHolderClickedInterface {
    val moreTvShowsList: MutableLiveData<MutableList<Any>> = MutableLiveData()
    private val repo = MoreTvShowsRepo()

    val secondActivityMLD = MutableLiveData<Pair<String, String>>()
    override fun onViewHolderClicked(movieId: String) {

        val movie = moreTvShowsList.value?.get(0) as Result
        var a = Pair(movieId, movie.name)
        secondActivityMLD.value = a
    }

//    override fun onViewHolderClicked2(movieId: String, movieName: String) {
//        var a = Pair(movieId, movieName)
//        secondActivityMLD.value = a
//        }

    fun getMoreTvShowsList() {
        viewModelScope.launch {

            try {
                val response = repo.getAllTvData(tvId)

                if (response != null) {
                    onSuccessResponse(response)
                }
            } catch (error: Error) {
            }
        }
    }

    private fun onSuccessResponse(response: MutableList<Any>) {
        moreTvShowsList.value = response
    }
}
