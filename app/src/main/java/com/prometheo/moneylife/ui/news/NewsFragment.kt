package com.prometheo.moneylife.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.data.liveData.LoadingLiveData
import com.prometheo.moneylife.databinding.FragmentNewsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var binding: FragmentNewsBinding
    private val vm: NewsViewModel by viewModels()

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate ( inflater, container, false )
        return binding.root
    }

    override fun onViewCreated ( view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated (view, savedInstanceState)
        binding.rvNews.layoutManager = LinearLayoutManager ( context, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.adapter = adapter
        LoadingLiveData.get().setLoading ( false )
        vm.turnEvents.observe( viewLifecycleOwner, Observer { turnEvents ->
            adapter.update (turnEvents.map { item ->
                NewsGroupieItem ( item )
            })
            binding.tvEmptyNews.isVisible = turnEvents.isEmpty()
        })

        LoadingLiveData.get().loading.observe ( viewLifecycleOwner, Observer { loading ->
            binding.rvNews.isVisible = !loading
            binding.loadingIndicator.isVisible = loading
            binding.shimmerViewContainer.isVisible = loading
            binding.tvEmptyNews.isVisible = !loading
            if ( loading ) {
                binding.shimmerViewContainer.startShimmer()
            } else {
                binding.shimmerViewContainer.stopShimmer()
            }
        })
    }
}