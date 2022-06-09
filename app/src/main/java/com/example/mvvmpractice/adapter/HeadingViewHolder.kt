package com.example.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.HeadingBinding
import com.example.mvvmpractice.databinding.TvShowDetailsCardBinding
import com.example.mvvmpractice.model.HeadingModel
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.ViewHolderClickedInterface

class HeadingViewHolder(
    val binding: HeadingBinding,
    val clickedInterface: ViewHolderClickedInterface
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            viewGroup: ViewGroup,
            clickedInterface: ViewHolderClickedInterface
        ): HeadingViewHolder {

            val binding = DataBindingUtil.inflate<HeadingBinding>(
                inflater,
                R.layout.heading,
                viewGroup,
                false
            )
            return HeadingViewHolder(binding, clickedInterface)
        }
    }

    fun bind(headingText: HeadingModel) {

        binding.tvHeading.text = headingText.heading


//        binding.tvCardView.setOnClickListener{
//            clickedInterface.onViewHolderClicked(tvShow.id)
//        }
    }

}