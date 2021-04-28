package com.prometheo.moneylife.ui.investments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.prometheo.moneylife.databinding.DialogNewInvestmentBinding

class NewInvestmentDialogFragment(
    private val name: String,
    private val onConfirmListener: (amount: Float) -> Unit
) : DialogFragment() {

    private var _binding: DialogNewInvestmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogNewInvestmentBinding.inflate(LayoutInflater.from(context)).apply {
            investmentName.text = name
            investButton.setOnClickListener {
                val amount: Float = amountField.text.toString().toFloat()
                onConfirmListener(amount)
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