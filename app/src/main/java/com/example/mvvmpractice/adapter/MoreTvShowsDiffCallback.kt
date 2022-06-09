package com.example.mvvmpractice.adapter

import androidx.recyclerview.widget.DiffUtil

class MoreTvShowsDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
    }
}