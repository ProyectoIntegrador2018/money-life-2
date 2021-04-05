package com.prometheo.moneylife.ui.health

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.data.models.Happiness
import com.xwray.groupie.viewbinding.BindableItem
import com.prometheo.moneylife.databinding.ItemHealthBinding

class HealthGroupieItem(
    private val happiness: Happiness,
) : BindableItem<ItemHealthBinding>() {

        override fun bind(viewBinding: ItemHealthBinding, position: Int) = with(viewBinding) {
            tvDescription.text = happiness.description.toString()
            tvPeriod.text = happiness.period.toString()
            tvDuration.text = happiness.duration.toString()
            tvPercentage.text = happiness.percentage.toString()
        }

        override fun getLayout(): Int {
            return R.layout.item_health
        }

        override fun initializeViewBinding(view: View): ItemHealthBinding {
            return ItemHealthBinding.bind(view)
        }
}