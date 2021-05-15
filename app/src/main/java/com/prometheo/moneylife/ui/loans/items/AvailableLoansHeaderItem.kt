package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemAvailableLoansHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class AvailableLoansHeaderItem : BindableItem<ItemAvailableLoansHeaderBinding>(){
    override fun getLayout(): Int =
        R.layout.item_available_loans_header
    override fun initializeViewBinding(view: View): ItemAvailableLoansHeaderBinding {
        return ItemAvailableLoansHeaderBinding.bind(
            view
        )
    }

    override fun bind(viewBinding: ItemAvailableLoansHeaderBinding, position: Int) {}
}