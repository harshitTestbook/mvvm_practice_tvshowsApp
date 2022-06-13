package com.example.mvvmpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.adapter.MoreTvShowsAdapter
import com.example.mvvmpractice.databinding.MoreTvShowsFragmentBinding
import com.example.mvvmpractice.viewmodel.MoreTvShowsViewModel
import com.example.mvvmpractice.viewmodel.MoreTvShowsViewModelFactory
import kotlinx.android.synthetic.main.more_tv_shows_fragment.view.*


class MoreTvShowsFragment : Fragment() {
    companion object {

        fun newInstance(bundle: Bundle) = MoreTvShowsFragment().apply {
            arguments = bundle
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        _binding = MoreTvShowsFragmentBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initBundle()
        initViewModel()
        initViewModelObserver()
        initData()
        initAdapter()
    }

    private var tvId: String? = ""
    private fun initBundle() {
        arguments?.let {
            tvId = it.getString("tvId")
        }
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

        viewModel.secondActivityMLD.observe(viewLifecycleOwner) {
            // remove them as soon as dev finishes
            onSecondActivityViewClicked(it)
        }
    }

    private fun onSecondActivityViewClicked(response: Pair<String, String>) {

        if (response != null)

            showDialog(response)


    }

    private fun showDialog(response: Pair<String, String>) {
//        val fm: FragmentManager = getSupportFragmentManager()
        val customDialogFragment =
            CustomDialogFragment.newInstance(response.second)
        if (customDialogFragment != null) {
            customDialogFragment.show(childFragmentManager, "fragment_edit_name")
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
    private lateinit var _binding: MoreTvShowsFragmentBinding
    private fun initAdapter() {

        layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = MoreTvShowsAdapter(viewModel)

        _binding.root.rv_more_movie_cards.adapter = adapter
        // use binding
        _binding.root.rv_more_movie_cards.layoutManager = layoutManager


    }
}