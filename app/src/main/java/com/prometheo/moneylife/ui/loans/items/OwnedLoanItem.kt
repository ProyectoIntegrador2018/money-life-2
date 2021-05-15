package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemOwnedLoanBinding
import com.xwray.groupie.viewbinding.BindableItem

class OwnedLoanItem(
    loanType: String,
    remainingPayments: Int,
    balance: Float,
    monthlyPayment: Float,
    onPayListener: () -> Unit = {},
): BindableItem<ItemOwnedLoanBinding>() {
    override fun getLayout(): Int = R.layout.item_owned_loans_header
    override fun initializeViewBinding(view: View): ItemOwnedLoanBinding {
        return ItemOwnedLoanBinding.bind(view)
    }

    override fun bind(viewBinding: ItemOwnedLoanBinding, position: Int) {}
}


