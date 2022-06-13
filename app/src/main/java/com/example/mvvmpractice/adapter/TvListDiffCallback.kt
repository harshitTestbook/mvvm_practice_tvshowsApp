package com.example.mvvmpractice.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmpractice.model.Result

class TvListDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is Result && newItem is Result) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return false
    }
}