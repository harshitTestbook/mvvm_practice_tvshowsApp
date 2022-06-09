package com.example.mvvmpractice

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.adapter.MoreTvShowsAdapter
import com.example.mvvmpractice.model.LatestTvShowDetailsModel
import com.example.mvvmpractice.viewmodel.MoreTvShowsViewModel
import com.example.mvvmpractice.viewmodel.MoreTvShowsViewModelFactory
import com.example.mvvmpractice.viewmodel.TvListViewModel
import com.example.mvvmpractice.viewmodel.TvListViewModelFactory
import kotlinx.android.synthetic.main.more_tv_shows_fragment.*


class MoreTvShowsFragment(intent: Intent) : Fragment() {
    private val tvId = intent.getStringExtra("tvId")

    companion object {

        fun newInstance(intent: Intent) =
            MoreTvShowsFragment(intent).apply {

            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.more_tv_shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initViewModel()
        initViewModelObserver()
        initData()
        initAdapter()
    }

    private lateinit var viewModel: MoreTvShowsViewModel
    private fun initViewModel() {

        @Suppress("DEPRECATION")
        viewModel =
            ViewModelProviders.of(this, tvId?.let { MoreTvShowsViewModelFactory(it) })
                .get(MoreTvShowsViewModel::class.java)
    }

    private fun initViewModelObserver() {
        viewModel.moreTvShowsList.observe(viewLifecycleOwner) {
            onGetMoreTvListData(it)
        }
    }

    private fun onGetMoreTvListData(response: MutableList<Any>) {

        if (response != null)
            submitToAdapterList(response)
    }

    private fun submitToAdapterList(response: MutableList<Any>) {
        adapter.submitList(response as ArrayList<*>)
    }

    private fun initData() {
        viewModel.getMoreTvShowsList()
    }


    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MoreTvShowsAdapter
    private fun initAdapter() {

        layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = MoreTvShowsAdapter(viewModel)
        rv_more_movie_cards.adapter = adapter
        rv_more_movie_cards.layoutManager = layoutManager


    }
}