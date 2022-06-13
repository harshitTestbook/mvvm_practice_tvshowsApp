package com.example.mvvmpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoreTvShowsViewModelFactory(val tvId: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoreTvShowsViewModel(tvId) as T
    }
}