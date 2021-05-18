package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemAvailableLoanBinding
import com.xwray.groupie.viewbinding.BindableItem

class AvailableLoanItem(
    private val loanType: String,
    private val duration: String,
    private val interestRate: String,
    private val onApplyListener: () -> Unit = {},
) : BindableItem<ItemAvailableLoanBinding>() {
    override fun getLayout(): Int = R.layout.item_available_loan
    override fun initializeViewBinding(view: View): ItemAvailableLoanBinding {
        return ItemAvailableLoanBinding.bind(view)
    }

    override fun bind(viewBinding: ItemAvailableLoanBinding, position: Int) {
        viewBinding.loanName.text = loanType
        viewBinding.duration.text = duration
        viewBinding.interestRate.text = interestRate
        viewBinding.applyButton.setOnClickListener { onApplyListener() }
    }
}
