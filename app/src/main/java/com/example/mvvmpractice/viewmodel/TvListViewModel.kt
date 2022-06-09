package com.example.mvvmpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.model.TvModel
import com.example.mvvmpractice.repository.TvListRepo
import kotlinx.coroutines.launch
import java.lang.Error

class TvListViewModel : ViewModel() , ViewHolderClickedInterface {

    val tvList: MutableLiveData<List<Result>> = MutableLiveData()
   private val tvRepo: TvListRepo = TvListRepo()

    fun getTvList() {

        viewModelScope.launch {

            try {
                val respone = tvRepo.fetchTvData()

                if (respone != null) {
                    onGetTvListResponseSuccess(respone)

                }
            } catch (error: Error) {

            }
        }
    }

   private fun onGetTvListResponseSuccess(response: TvModel) {
        tvList.value = response.results
    }

    val onMovieClickedMLD = MutableLiveData<String>()
    override fun onViewHolderClicked(movieId: String) {
        onMovieClickedMLD.value = movieId
    }
}