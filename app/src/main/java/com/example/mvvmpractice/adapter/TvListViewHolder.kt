package com.example.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.TvShowDetailsCardBinding
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.ViewHolderClickedInterface


class TvListViewHolder(
    private val binding: TvShowDetailsCardBinding,
    private val clickedInterface: ViewHolderClickedInterface
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            viewGroup: ViewGroup,
            clickedInterface: ViewHolderClickedInterface
        ): TvListViewHolder {

            val binding = DataBindingUtil.inflate<TvShowDetailsCardBinding>(
                inflater,
                R.layout.tv_show_details_card,
                viewGroup,
                false
            )
            return TvListViewHolder(binding, clickedInterface)
        }
    }

    fun bind(tvShow: Result) {

        binding.tvName.text = "${tvShow.name}"
        binding.tvOverview.text = "Overview:- ${tvShow.overview}"
        Glide.with(binding.iv).load("https://image.tmdb.org/t/p/original${tvShow.backdrop_path}")
            .into(binding.iv)


        binding.tvCardView.setOnClickListener {


            clickedInterface.onViewHolderClicked(tvShow.id)
        }
    }

}