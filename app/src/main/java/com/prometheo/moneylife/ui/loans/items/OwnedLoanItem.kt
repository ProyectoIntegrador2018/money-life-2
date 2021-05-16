package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemOwnedLoanBinding
import com.xwray.groupie.viewbinding.BindableItem

class OwnedLoanItem(
    private val loanType: String,
    private val remainingPayments: Int,
    private val balance: String,
    private val monthlyPayment: String,
    private val onPayListener: () -> Unit = {},
) : BindableItem<ItemOwnedLoanBinding>() {
    override fun getLayout(): Int = R.layout.item_owned_loan
    override fun initializeViewBinding(view: View): ItemOwnedLoanBinding {
        return ItemOwnedLoanBinding.bind(view)
    }

    override fun bind(viewBinding: ItemOwnedLoanBinding, position: Int) {
        viewBinding.loanName.text = loanType
        viewBinding.balance.text = "-$${balance}"
        viewBinding.remainingPayments.text = "Meses restantes: $remainingPayments"
        viewBinding.monthlyPayment.text = "Mensualidad: $$monthlyPayment"
        viewBinding.root.setOnClickListener { onPayListener() }
    }
}


