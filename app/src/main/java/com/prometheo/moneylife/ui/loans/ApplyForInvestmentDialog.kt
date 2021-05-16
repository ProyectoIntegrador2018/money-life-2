package com.prometheo.moneylife.ui.loans

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.prometheo.moneylife.databinding.DialogApplyForLoanBinding

class ApplyForInvestmentDialog(
    private val name: String,
    private val onConfirmListener: (deposit: Int, totalValue: Int) -> Unit
) : DialogFragment() {

    private var _binding: DialogApplyForLoanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogApplyForLoanBinding.inflate(LayoutInflater.from(context)).apply {
            loanName.text = name
            payButton.setOnClickListener {
                val totalValue = amountField.text.toString().toInt()
                val deposit = depositField.text.toString().toInt()

                onConfirmListener(deposit, totalValue)
                dismiss()

            }
            cancelButton.setOnClickListener { dismiss() }
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}