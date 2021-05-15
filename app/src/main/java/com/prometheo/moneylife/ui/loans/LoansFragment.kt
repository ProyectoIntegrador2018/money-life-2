package com.prometheo.moneylife.ui.loans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.databinding.FragmentLoansBinding
import com.prometheo.moneylife.ui.loans.items.AvailableLoanItem
import com.prometheo.moneylife.ui.loans.items.AvailableLoansHeaderItem
import com.prometheo.moneylife.ui.loans.items.OwnedLoanItem
import com.prometheo.moneylife.ui.loans.items.OwnedLoansHeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoansFragment : Fragment() {

    companion object {
        fun newInstance() = LoansFragment()
    }

    private val vm: LoansViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var _binding: FragmentLoansBinding? = null
    private val binding get() = _binding!!

    private var currentEditingInvestmentPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoansBinding.inflate(inflater, container, false).apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        vm.state.observe(viewLifecycleOwner, Observer { state ->
            binding.loadingIndicator.isVisible = state.loading


        })

        vm.loans.observe(viewLifecycleOwner, Observer { loans ->
            adapter.clear()
            adapter.add(OwnedLoansHeaderItem())
            adapter.addAll(loans.userLoans.map {
                OwnedLoanItem(
                    TODO()
                )
            })
            adapter.add(AvailableLoansHeaderItem())
            adapter.addAll(loans.availableLoans.map {
                AvailableLoanItem(
                    TODO()
                )
            })
        })
    }

    override fun onResume() {
        super.onResume()
        vm.loadData()
    }
}