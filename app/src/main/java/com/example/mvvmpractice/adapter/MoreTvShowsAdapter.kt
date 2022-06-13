package com.example.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.R
import com.example.mvvmpractice.model.HeadingModel
import com.example.mvvmpractice.model.LatestTvShowDetailsModel
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.ViewHolderClickedInterface

class MoreTvShowsAdapter(val clickedInterface: ViewHolderClickedInterface) :
    ListAdapter<Any, RecyclerView.ViewHolder>(MoreTvShowsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            R.layout.heading -> viewHolder =
                HeadingViewHolder.create(inflater, parent, clickedInterface)
            R.layout.tv_show_details_card -> viewHolder =
                TvListViewHolder.create(inflater, parent, clickedInterface)
            R.layout.latest_tv_show_details_card -> viewHolder =
                LatestTvShowViewHolder.create(inflater, parent, clickedInterface)

        }

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)
        when (holder) {
            is HeadingViewHolder -> holder.bind(item as HeadingModel)
            is TvListViewHolder -> holder.bind(item as Result)
            is LatestTvShowViewHolder -> holder.bind(item as LatestTvShowDetailsModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)

        val item = getItem(position)
        var itemViewType = 0

        itemViewType = when (item) {
            is HeadingModel -> R.layout.heading
            is Result -> R.layout.tv_show_details_card
            is LatestTvShowDetailsModel -> R.layout.latest_tv_show_details_card
            else -> 0
        }

        return itemViewType

    }
}