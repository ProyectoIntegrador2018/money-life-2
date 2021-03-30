package com.prometheo.moneylife.ui.turn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.FragmentTurnBinding
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import kotlin.math.round

@AndroidEntryPoint
class TurnFragment : Fragment() {

    private lateinit var binding: FragmentTurnBinding
    private val vm: TurnViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTurnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getTurnData()
        binding.cvInvest.setOnClickListener { investButtonPressed() }
        binding.cvFun.setOnClickListener { funButtonPressed() }
        binding.cvPersonalProperty.setOnClickListener { personalPropertyButtonPressed() }
        binding.cvLaboral.setOnClickListener { laboralButtonPressed() }
        binding.fabNextTurn.setOnClickListener { nextTurnButtonPressed() }
        vm.turnData.observe(viewLifecycleOwner, Observer { turnData ->
            binding.tvWeekNumber.text = getString(R.string.tv_week_number, turnData.turnNumber)
            binding.tvIncomeAmount.text = getString(R.string.tv_money, turnData.income.toBigDecimal().setScale(2, RoundingMode.UP).toDouble())
            binding.tvBalanceAmount.text = getString(R.string.tv_money, turnData.balance)
            binding.tvExpensesAmount.text = getString(R.string.tv_money, turnData.expenses)
        })
        vm.loading.observe(viewLifecycleOwner, Observer { loading ->
            binding.loadingIndicator.isVisible = loading
        })
    }

    fun investButtonPressed() {
        println("Invertir")
    }

    fun funButtonPressed() {
        println("Fun")
    }

    fun personalPropertyButtonPressed() {
        println("Personal Property")
    }

    fun laboralButtonPressed() {
        println("Laboral")
    }

    fun nextTurnButtonPressed() {
        vm.nextTurn()
    }
}