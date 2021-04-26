package com.prometheo.moneylife.ui.investments

import android.view.View
import androidx.core.view.isVisible
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemOwnedInvestmentBinding
import com.xwray.groupie.viewbinding.BindableItem

class OwnedInvestmentItem(
    private val id: Int,
    private val investmentName: String,
    private val category: String,
    private val currentBalance: Number,
    private val initialBalance: Number,
    private val initialContribution: Number,
    private val onEditListener: () -> Unit = {},
    private val onInvestListener: (amount: Number) -> Unit = {},
    private val onWithdrawListener: (amount: Number) -> Unit = {},
) : BindableItem<ItemOwnedInvestmentBinding>() {
    private lateinit var _viewBinding: ItemOwnedInvestmentBinding

    override fun bind(viewBinding: ItemOwnedInvestmentBinding, position: Int) {
        _viewBinding = viewBinding

        viewBinding.investmentName.text = investmentName
        viewBinding.category.text = category
        viewBinding.currentBalance.text = currentBalance.toString()
        viewBinding.initialBalance.text = initialBalance.toString()
        viewBinding.initialContribution.text = initialContribution.toString()

        viewBinding.editButton.setOnClickListener {
            onEditListener()
            toggleEditMode()
        }

        viewBinding.investButton.setOnClickListener {
            val amount: Number = viewBinding.amountField.text.toString().toFloat()
            onInvestListener(amount)
        }

        viewBinding.withdrawButton.setOnClickListener {
            val amount: Number = viewBinding.amountField.text.toString().toFloat()
            onWithdrawListener(amount)
        }

        viewBinding.withdrawAllButton.setOnClickListener {
            onWithdrawListener(currentBalance)
        }
    }

    fun toggleEditMode() {
        _viewBinding.editButton.isVisible = !_viewBinding.editButton.isVisible
        _viewBinding.investButton.isVisible = !_viewBinding.investButton.isVisible
        _viewBinding.withdrawButton.isVisible = !_viewBinding.withdrawButton.isVisible
        _viewBinding.withdrawAllButton.isVisible = !_viewBinding.withdrawAllButton.isVisible
        _viewBinding.amountFieldHint.isVisible = !_viewBinding.amountFieldHint.isVisible
    }

    override fun getLayout(): Int {
        return R.layout.item_owned_investment
    }

    override fun initializeViewBinding(view: View): ItemOwnedInvestmentBinding {
        return ItemOwnedInvestmentBinding.bind(view)
    }
}
