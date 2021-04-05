package com.prometheo.moneylife.ui.turn

import android.view.View
import com.prometheo.moneylife.R
import com.prometheo.moneylife.data.models.TurnAction
import com.prometheo.moneylife.data.models.TurnActionCategory
import com.prometheo.moneylife.databinding.ItemTurnActionBinding
import com.xwray.groupie.viewbinding.BindableItem

class TurnActionGroupieItem(
    private val turnAction: TurnAction,
    private val onClickListener: (Int, TurnActionCategory) -> Unit
) : BindableItem<ItemTurnActionBinding>() {

    override fun bind(viewBinding: ItemTurnActionBinding, position: Int) = with(viewBinding) {
        root.setOnClickListener { onClickListener(turnAction.actionId, turnAction.category) }
        tvActionDescription.text = turnAction.description
        var parseInfluences = ""
        turnAction.influences.forEachIndexed { index, influence ->
            // TODO: parse this to the correct format
            parseInfluences += "${influence.influences} ${influence.amount} / ${influence.period} x ${influence.duration}"
            if (index < turnAction.influences.size-1) {
                parseInfluences += "\n"
            }
        }
        tvInfluences.text = parseInfluences
    }

    override fun getLayout(): Int {
        return R.layout.item_turn_action
    }

    override fun initializeViewBinding(view: View): ItemTurnActionBinding {
        return ItemTurnActionBinding.bind(view)
    }
}