package com.example.mvvmpractice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.adapter.TvListAdapter
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.TvListViewModel
import com.example.mvvmpractice.viewmodel.TvListViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*

class TvListFragment() : Fragment() {

    companion object {
        fun newInstance() = TvListFragment().apply {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("YO", "onCreateView")

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("YO", "onViewCreated")

        init()
    }

    private fun init() {

        Log.d("YO", "init")

        initViewModel()
        initViewModelObserver()
        initData()
        initAdapter()
    }

    private fun initData() {
        Log.d("YO", "initData")

        viewModel.getTvList()
    }

    private fun initViewModelObserver() {

        Log.d("YO", "initViewModelObserver")

        viewModel.tvList.observe(viewLifecycleOwner) {
            onGetTvListData(it)
        }

        viewModel.onMovieClickedMLD.observe(viewLifecycleOwner , {
            onTvShowClicked(it)
        })
    }

    private fun onTvShowClicked(tvId: String?) {
        tvId?.let {
            MoreTvShowsActivity.start(requireContext() , tvId )
        }
    }

    private fun onGetTvListData(response: List<Result>?) {
        if (response != null) {
            adapterListSubmit(response)
        }

    }

    private fun adapterListSubmit(response: List<Result>?) {
        //Submitting updated list of Tv shows to adapter
        adapter.submitList(response as ArrayList<*>)
    }


    private lateinit var viewModel: TvListViewModel
    private fun initViewModel() {

        Log.d("YO", "initViewModel")

        @Suppress("DEPRECATION")

        viewModel =
            ViewModelProviders.of(this, TvListViewModelFactory()).get(TvListViewModel::class.java)

    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter:TvListAdapter
    private fun initAdapter() {
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        //sending viewModel to TvListAdapter in order to implement onClick feature
        adapter=TvListAdapter(viewModel)
        rv_movie_cards.adapter = adapter
        rv_movie_cards.layoutManager = linearLayoutManager

    }
}