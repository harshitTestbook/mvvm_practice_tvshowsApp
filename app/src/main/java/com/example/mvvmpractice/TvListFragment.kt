package com.example.mvvmpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.adapter.TvListAdapter
import com.example.mvvmpractice.databinding.MainFragmentBinding
import com.example.mvvmpractice.model.Result
import com.example.mvvmpractice.viewmodel.TvListViewModel
import com.example.mvvmpractice.viewmodel.TvListViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.view.*

class TvListFragment : Fragment() {

    companion object {
        fun newInstance() = TvListFragment().apply {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return _binding!!.root
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

    private fun initData() {

        viewModel.getTvList()
    }

    private fun initViewModelObserver() {

        viewModel.tvList.observe(viewLifecycleOwner) {
            onGetTvListData(it)
        }

        viewModel.onMovieClickedMLD.observe(viewLifecycleOwner, {
            onTvShowClicked(it)
        })
    }

    private fun onTvShowClicked(tvId: String?) {
        tvId?.let {
            MoreTvShowsActivity.start(requireContext(), tvId)
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


        @Suppress("DEPRECATION")
        viewModel =
            ViewModelProviders.of(this, TvListViewModelFactory()).get(TvListViewModel::class.java)

    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: TvListAdapter
    private var _binding: MainFragmentBinding? = null


    private fun initAdapter() {
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        //sending viewModel to TvListAdapter in order to implement onClick feature
        adapter = TvListAdapter(viewModel)

        _binding?.root?.rv_movie_cards?.adapter = adapter

        _binding?.root?.rv_movie_cards?.layoutManager = linearLayoutManager

    }
}