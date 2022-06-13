package com.example.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.LatestTvShowDetailsCardBinding
import com.example.mvvmpractice.model.LatestTvShowDetailsModel
import com.example.mvvmpractice.viewmodel.ViewHolderClickedInterface

class LatestTvShowViewHolder(
    private val binding: LatestTvShowDetailsCardBinding,
    val clickedInterface: ViewHolderClickedInterface
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            viewGroup: ViewGroup,
            clickedInterface: ViewHolderClickedInterface
        ): LatestTvShowViewHolder {

            val binding = DataBindingUtil.inflate<LatestTvShowDetailsCardBinding>(
                inflater,
                R.layout.latest_tv_show_details_card,
                viewGroup,
                false
            )
            return LatestTvShowViewHolder(binding, clickedInterface)
        }
    }

    fun bind(tvShow: LatestTvShowDetailsModel) {

        binding.tvLatestName.text = tvShow.name
        var lang: String = ""
        tvShow.languages.forEach { lang.plus(it).plus(",").plus(" ") }
        binding.tvLatestLang.text = tvShow.languages.toString()


    }
}