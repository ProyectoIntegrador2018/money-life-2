package com.prometheo.moneylife.ui.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.databinding.FragmentLoansBinding
import com.prometheo.moneylife.ui.loans.items.*
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
            binding.rv.isVisible = !state.loading
            binding.loadingIndicator.isVisible = state.loading
            binding.shimmerViewContainer.isVisible = state.loading
            if (state.loading) {
                binding.shimmerViewContainer.startShimmer()
            } else {
                binding.shimmerViewContainer.stopShimmer()
            }
        })

        vm.loans.observe(viewLifecycleOwner, Observer { loans ->
            adapter.clear()
            if (loans.userLoans.isNotEmpty()) {
                adapter.add(OwnedLoansHeaderItem())
                adapter.addAll(loans.userLoans.map {
                    OwnedLoanItem(
                        loanType = it.type,
                        balance = it.balance,
                        monthlyPayment = it.monthlyPayment,
                        remainingPayments = it.monthsRemaining,
                        onPayListener = {
                            showPayBalanceDialog(
                                it.id,
                                it.type,
                                it.balance.toFloat()
                            )
                        }
                    )
                })
            }
            if (loans.userLoans.isNotEmpty() && loans.availableLoans.isNotEmpty()) {
                adapter.add(SeparatorItem())
            }
            if (loans.availableLoans.isNotEmpty()) {
                adapter.add(AvailableLoansHeaderItem())
                adapter.addAll(loans.availableLoans.map {
                    AvailableLoanItem(
                        loanType = it.type,
                        duration = it.duration,
                        interestRate = it.interestRate,
                        onApplyListener = { showApplyForInvestmentDialog(it.id, it.type) }
                    )
                })
            }
        })
    }

    override fun onResume() {
        super.onResume()
        vm.loadData()
    }

    private fun showPayBalanceDialog(
        loanId: Int,
        loanName: String,
        remainingBalance: Float
    ) {
        PayLoanDialogFragment(
            name = loanName,
            totalLeft = remainingBalance,
            onConfirmListener = { amount ->
                vm.payInvestment(loanId, amount)
            }
        ).show(parentFragmentManager, null)
    }

    private fun showApplyForInvestmentDialog(
        loanId: Int,
        loanName: String,
    ) {
        ApplyForInvestmentDialog(
            name = loanName,
            onConfirmListener = { deposit, totalValue ->
                vm.applyForInvestment(
                    loanId = loanId,
                    totalValue = totalValue,
                    deposit = deposit
                )
            }
        ).show(parentFragmentManager, null)
    }
}