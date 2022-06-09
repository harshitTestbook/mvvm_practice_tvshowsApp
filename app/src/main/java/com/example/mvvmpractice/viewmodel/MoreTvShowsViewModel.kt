package com.example.mvvmpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpractice.repository.MoreTvShowsRepo
import kotlinx.coroutines.launch
import kotlin.Error

class MoreTvShowsViewModel(val tvId:String) : ViewModel(), ViewHolderClickedInterface {
    val moreTvShowsList: MutableLiveData<MutableList<Any>> = MutableLiveData()
   private val repo = MoreTvShowsRepo()


    override fun onViewHolderClicked(movieId: String) {

    }

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
