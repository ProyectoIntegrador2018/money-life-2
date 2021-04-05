package com.prometheo.moneylife.ui.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.FragmentHealthBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HealthFragment : Fragment() {

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var binding: FragmentHealthBinding
    private val vm: HealthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getHappinessData()
        vm.happinessData.observe(viewLifecycleOwner, Observer { happinessData ->
            binding.rvHealth.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvHealth.adapter = adapter
            adapter.update(happinessData.map { item ->
                HealthGroupieItem(item)
            })
        })
        vm.loading.observe(viewLifecycleOwner, Observer { loading ->
            binding.loadingIndicator.isVisible = loading
        })
    }


}