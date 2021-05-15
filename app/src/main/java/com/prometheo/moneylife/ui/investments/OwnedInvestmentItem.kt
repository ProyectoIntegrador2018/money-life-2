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
    private val currentBalance: Float,
    private val initialBalance: Float,
    private val initialContribution: Float,
    private val onEditListener: () -> Unit = {},
    private val onEditCanceledListener: () -> Unit = {},
    private val onInvestListener: (amount: Int) -> Unit = {},
    private val onWithdrawListener: (amount: Int) -> Unit = {},
    private val onSellListener: () -> Unit = {}
) : BindableItem<ItemOwnedInvestmentBinding>() {
    private lateinit var _viewBinding: ItemOwnedInvestmentBinding
    private var editable = false

    override fun bind(viewBinding: ItemOwnedInvestmentBinding, position: Int) {
        _viewBinding = viewBinding

        viewBinding.investmentName.text = investmentName
        viewBinding.category.text = category
        viewBinding.currentBalance.text = viewBinding.root.context.getString(
            R.string.tv_money, currentBalance)
        viewBinding.initialBalance.text = viewBinding.root.context.getString(
            R.string.tv_money, initialBalance)
        viewBinding.initialContribution.text = viewBinding.root.context.getString(
            R.string.tv_money, initialContribution)

        _viewBinding.investButton.isVisible = false
        _viewBinding.withdrawButton.isVisible = false
        _viewBinding.withdrawAllButton.isVisible = false
        _viewBinding.amountFieldHint.isVisible = false

        viewBinding.editButton.setOnClickListener { toggleEditMode() }
        viewBinding.investButton.setOnClickListener {
            val amount = viewBinding.amountField.text.toString().toInt()
            onInvestListener(amount)
        }
        viewBinding.withdrawButton.setOnClickListener {
            val amount = viewBinding.amountField.text.toString().toInt()

            if (amount > currentBalance) {
                viewBinding.amountFieldHint.error = "No puedes retirar del saldo actual"
            }

            onWithdrawListener(amount)
        }
        viewBinding.withdrawAllButton.setOnClickListener {
            onSellListener()
        }
    }

    fun toggleEditMode() {
        editable = !editable

        _viewBinding.investButton.isVisible = editable
        _viewBinding.withdrawButton.isVisible = editable
        _viewBinding.withdrawAllButton.isVisible = editable
        _viewBinding.amountFieldHint.isVisible = editable

        if (editable) {
            onEditListener()
        } else {
            onEditCanceledListener()
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_owned_investment
    }

    override fun initializeViewBinding(view: View): ItemOwnedInvestmentBinding {
        return ItemOwnedInvestmentBinding.bind(view)
    }
}
