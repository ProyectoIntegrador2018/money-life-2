package com.prometheo.moneylife.ui.investments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.FragmentCurrentInvestmentsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrentInvestmentsFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentInvestmentsFragment()
    }

    private val vm: CurrentInvestmentsViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var _binding: FragmentCurrentInvestmentsBinding? = null
    private val binding get() = _binding!!

    private var currentEditingInvestmentPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentInvestmentsBinding.inflate(inflater, container, false).apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            fabNewInvestment.setOnClickListener { openNewInvestmentScreen() }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.state.observe(viewLifecycleOwner, Observer { state ->
            binding.loadingIndicator.isVisible = state.loading
        })

        vm.currentInvestments.observe(viewLifecycleOwner, Observer { currentInvestments ->
            binding.emptyMessage.isVisible = currentInvestments.isEmpty()
            adapter.update(currentInvestments.mapIndexed { position, investment ->

                OwnedInvestmentItem(
                    id = investment.id,
                    investmentName = investment.name,
                    category = investment.type,
                    currentBalance = investment.currentBalance,
                    initialBalance = investment.initialBalance,
                    initialContribution = investment.initialContribution,
                    onEditListener = { toggleEditMode(position) },
                    onEditCanceledListener = {currentEditingInvestmentPosition = null },
                    onInvestListener = { amount -> vm.invest(investmentId = investment.id, amount = amount) },
                    onWithdrawListener = { amount -> vm.withdraw(investmentId = investment.id, amount = amount) },
                    onSellListener = { vm.sell(investmentId = investment.id) },
                    onShowGraphListener = { investmentId -> showInvestmentBalanceGraph(investmentId) }
                )
            })
        })
    }

    override fun onResume() {
        super.onResume()
        vm.loadData()
    }

    private fun toggleEditMode(position: Int) {
        if (position == currentEditingInvestmentPosition) {
            return
        }

        currentEditingInvestmentPosition?.let { lastPosition ->
            (adapter.getItem(lastPosition) as OwnedInvestmentItem).toggleEditMode()
        }

        currentEditingInvestmentPosition = position
    }

    fun showInvestmentBalanceGraph(investmentId: Int) {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val queryResponse = vm.retrieveHistoricalInvestmentBalance(investmentId)
            InvestmentBalanceGraphDialogFragment(queryResponse).show(parentFragmentManager, null)
        }
    }

    private fun openNewInvestmentScreen() {
        parentFragmentManager.commit {
            add(R.id.nav_host_fragment, AvailableInvestmentsFragment.newInstance())
            addToBackStack(null)
        }
    }
}