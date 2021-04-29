package com.prometheo.moneylife.ui.turn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.prometheo.moneylife.R
import com.prometheo.moneylife.data.models.TurnAction
import com.prometheo.moneylife.data.models.TurnActionCategory
import com.prometheo.moneylife.databinding.FragmentTurnBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurnFragment : Fragment() {

    private val vm: TurnViewModel by viewModels()
    private lateinit var binding: FragmentTurnBinding

    private var investActions = listOf<TurnAction>()
    private var funActions = listOf<TurnAction>()
    private var goodsActions = listOf<TurnAction>()
    private var workActions = listOf<TurnAction>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTurnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getTurnData()
        binding.cvInvest.setOnClickListener { showDialog(investActions) }
        binding.cvFun.setOnClickListener { showDialog(funActions) }
        binding.cvGoods.setOnClickListener { showDialog(goodsActions) }
        binding.cvWork.setOnClickListener { showDialog(workActions) }
        binding.fabNextTurn.setOnClickListener { vm.nextTurn() }
        vm.turnData.observe(viewLifecycleOwner, { turnData ->
            binding.tvWeekNumber.text = getString(R.string.tv_week_number, turnData.turnNumber)
            binding.tvIncomeAmount.text = getString(R.string.tv_money, turnData.income)
            binding.tvBalanceAmount.text = getString(R.string.tv_money, turnData.balance)
            binding.tvExpensesAmount.text = getString(R.string.tv_money, turnData.expenses)
        })
        vm.turnActions.observe(viewLifecycleOwner, { actions ->
            investActions = actions.filter { it.category == TurnActionCategory.INVESTMENT }
            funActions = actions.filter { it.category == TurnActionCategory.FUN }
            goodsActions = actions.filter { it.category == TurnActionCategory.GOODS }
            workActions = actions.filter { it.category == TurnActionCategory.WORK }

            resetTurnActions()
        })
        vm.loading.observe(viewLifecycleOwner, { loading ->
            binding.content.isVisible = !loading
            binding.fabNextTurn.isVisible = !loading
            binding.loadingIndicator.isVisible = loading
            binding.shimmerViewContainer.isVisible = loading
            if (loading) {
                binding.shimmerViewContainer.startShimmer()
            } else {
                binding.shimmerViewContainer.stopShimmer()
            }
        })
        vm.getBlockTurnActionCategory().observe(viewLifecycleOwner, { category ->
            // Set that category to blocked
            when (category) {
                TurnActionCategory.INVESTMENT -> {
                    binding.cvInvest.isClickable = false
                    binding.ivInvestBlock.isVisible = true
                }
                TurnActionCategory.FUN -> {
                    binding.cvFun.isClickable = false
                    binding.ivFunBlock.isVisible = true
                }
                TurnActionCategory.GOODS -> {
                    binding.cvGoods.isClickable = false
                    binding.ivGoodsBlock.isVisible = true
                }
                TurnActionCategory.WORK -> {
                    binding.cvWork.isClickable = false
                    binding.ivWorkBlock.isVisible = true
                }
            }
        })
        vm.getMessage().observe(viewLifecycleOwner, { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showDialog(actions: List<TurnAction>) {
        TurnActionsDialogFragment(actions) { actionId, category ->
            // OnItemClickListener
            vm.selectTurnAction(actionId, category)
        }.show(requireActivity().supportFragmentManager, TurnActionsDialogFragment.TAG)
    }

    private fun resetTurnActions() {
        binding.cvInvest.isClickable = true
        binding.ivInvestBlock.isVisible = false

        binding.cvFun.isClickable = true
        binding.ivFunBlock.isVisible = false

        binding.cvGoods.isClickable = true
        binding.ivGoodsBlock.isVisible = false

        binding.cvWork.isClickable = true
        binding.ivWorkBlock.isVisible = false
    }
}