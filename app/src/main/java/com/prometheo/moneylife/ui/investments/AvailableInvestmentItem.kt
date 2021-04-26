package com.prometheo.moneylife.ui.investments

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemAvailableInvestmentBinding
import com.xwray.groupie.viewbinding.BindableItem

class AvailableInvestmentItem(
    private val investmentName: String,
    private val category: String,
    private val range: String,
    private val onClickListener: () -> Unit = {},
) : BindableItem<ItemAvailableInvestmentBinding>() {
    private lateinit var _viewBinding: ItemAvailableInvestmentBinding

    override fun bind(viewBinding: ItemAvailableInvestmentBinding, position: Int) {
        _viewBinding = viewBinding

        viewBinding.investmentName.text = investmentName
        viewBinding.category.text = category
        viewBinding.range.text = range
        viewBinding.root.setOnClickListener { onClickListener() }
    }

    override fun getLayout(): Int {
        return R.layout.item_available_investment
    }

    override fun initializeViewBinding(view: View): ItemAvailableInvestmentBinding {
        return ItemAvailableInvestmentBinding.bind(view)
    }
}