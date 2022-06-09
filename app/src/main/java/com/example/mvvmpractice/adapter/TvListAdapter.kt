package com.example.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.R
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.ViewHolderClickedInterface

class TvListAdapter(val clickedInterface: ViewHolderClickedInterface) :
    ListAdapter<Any, RecyclerView.ViewHolder>(TvListDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            R.layout.tv_show_details_card -> viewHolder =
                TvListViewHolder.create(inflater, parent, clickedInterface)
        }

        return viewHolder as TvListViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)

        when (holder) {
            is TvListViewHolder -> holder.bind(item as Result)
        }
    }


    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)

        val item = getItem(position)
        var itemViewType = 0
        when (item) {
            is Result -> itemViewType = R.layout.tv_show_details_card


        }

        return itemViewType
    }


}