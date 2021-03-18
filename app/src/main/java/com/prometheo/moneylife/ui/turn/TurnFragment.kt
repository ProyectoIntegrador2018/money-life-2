package com.prometheo.moneylife.ui.turn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prometheo.moneylife.databinding.FragmentTurnBinding

class TurnFragment : Fragment() {

    private lateinit var binding: FragmentTurnBinding

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
        binding.cvInvest.setOnClickListener { investButtonPressed() }
        binding.cvFun.setOnClickListener { funButtonPressed() }
        binding.cvPersonalProperty.setOnClickListener { personalPropertyButtonPressed() }
        binding.cvLaboral.setOnClickListener { laboralButtonPressed() }
        binding.fabNextTurn.setOnClickListener { nextTurnButtonPressed() }
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
        println("Next Turn")
    }
}