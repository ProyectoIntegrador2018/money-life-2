package com.prometheo.moneylife.ui.turn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prometheo.moneylife.databinding.FragmentTurnBinding
import dagger.hilt.android.AndroidEntryPoint

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
            binding.tvWeekNumber.text = turnData.turnNumber.toString() //TODO: add string resources
            binding.tvIncomeAmount.text = turnData.income.toString()
            binding.tvBalanceAmount.text = turnData.balance.toString()
            binding.tvExpensesAmount.text = turnData.expenses.toString()
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