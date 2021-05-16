package com.prometheo.moneylife.ui.news

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.data.models.TurnEvent
import com.prometheo.moneylife.databinding.ItemNewsBinding
import com.xwray.groupie.viewbinding.BindableItem

class NewsGroupieItem (
    private val turnEvent: TurnEvent
) : BindableItem<ItemNewsBinding>() {

    override fun bind(viewBinding: ItemNewsBinding, position: Int) = with(viewBinding){
        tvNewsDate.text = turnEvent.turnNumber.toString()
        tvNewsText.text = turnEvent.description.toString()
    }

    override fun getLayout(): Int {
        return R.layout.item_news
    }

    override fun initializeViewBinding(view: View): ItemNewsBinding {
        return ItemNewsBinding.bind(view)
    }
}