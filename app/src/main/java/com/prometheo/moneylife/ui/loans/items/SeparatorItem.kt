package com.prometheo.moneylife.ui.loans.items

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.ItemSeparatorBinding
import com.xwray.groupie.viewbinding.BindableItem

class SeparatorItem : BindableItem<ItemSeparatorBinding>() {
    override fun getLayout(): Int = R.layout.item_separator
    override fun initializeViewBinding(view: View): ItemSeparatorBinding {
        return ItemSeparatorBinding.bind(view)
    }

    override fun bind(viewBinding: ItemSeparatorBinding, position: Int) {}
}
