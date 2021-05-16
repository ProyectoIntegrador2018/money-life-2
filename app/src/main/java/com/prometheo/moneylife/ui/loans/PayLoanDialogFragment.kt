package com.prometheo.moneylife.ui.loans

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.DialogPayLoanBinding

class PayLoanDialogFragment(
    private val name: String,
    private val totalLeft: Float,
    private val onConfirmListener: (amount: Int) -> Unit
) : DialogFragment() {

    private var _binding: DialogPayLoanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogPayLoanBinding.inflate(LayoutInflater.from(context)).apply {
            loanName.text = name
            balance.text = "Restante: $$totalLeft"
            payButton.setOnClickListener {
                val amount = amountField.text.toString().toInt()

                if (amount > totalLeft) {
                    amountFieldHint.error = getString(R.string.cant_pay_more_than_max)
                } else {
                    onConfirmListener(amount)
                    dismiss()
                }
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