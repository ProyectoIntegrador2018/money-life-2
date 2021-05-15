package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemAvailableLoanBinding
import com.prometheo.moneylife.databinding.ItemOwnedLoansHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class AvailableLoanItem(
    loanType: String,
    duration: String,
    interestRate: String,
    onApplyListener: () -> Unit = {},
) : BindableItem<ItemAvailableLoanBinding>() {
    override fun getLayout(): Int = R.layout.item_owned_loans_header
    override fun initializeViewBinding(view: View): ItemAvailableLoanBinding {
        return ItemAvailableLoanBinding.bind(view)
    }

    override fun bind(viewBinding: ItemAvailableLoanBinding, position: Int) {}
}
