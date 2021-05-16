package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemOwnedLoansHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class OwnedLoansHeaderItem : BindableItem<ItemOwnedLoansHeaderBinding>() {
    override fun getLayout(): Int = R.layout.item_owned_loans_header
    override fun initializeViewBinding(view: View): ItemOwnedLoansHeaderBinding {
        return ItemOwnedLoansHeaderBinding.bind(view)
    }

    override fun bind(viewBinding: ItemOwnedLoansHeaderBinding, position: Int) {}
}

