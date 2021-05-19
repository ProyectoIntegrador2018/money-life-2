package com.prometheo.moneylife.ui.investments.available

import android.graphics.Color
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
        viewBinding.range.text = range
        viewBinding.root.setOnClickListener { onClickListener() }
        viewBinding.category.text = category.replace("_", " ")
        when (category) {
            "Tecnologia" -> {
                viewBinding.category.setTextColor(Color.parseColor("#EE0000"))
                viewBinding.category.background.setTint(Color.parseColor("#20EE0000"))
            }
            "Telecomunicaciones" -> {
                viewBinding.category.setTextColor(Color.parseColor("#6002EE"))
                viewBinding.category.background.setTint(Color.parseColor("#206002EE"))
            }
            "Indice_bursatil" -> {
                viewBinding.category.setTextColor(Color.parseColor("#64DD17"))
                viewBinding.category.background.setTint(Color.parseColor("#2064DD17"))
            }
            "Consumo" -> {
                viewBinding.category.setTextColor(Color.parseColor("#4A148C"))
                viewBinding.category.background.setTint(Color.parseColor("#204A148C"))
            }
            "Servicios_Financieros" -> {
                viewBinding.category.setTextColor(Color.parseColor("#004D40"))
                viewBinding.category.background.setTint(Color.parseColor("#20004D40"))
            }
            "Materiales" -> {
                viewBinding.category.setTextColor(Color.parseColor("#F57C00"))
                viewBinding.category.background.setTint(Color.parseColor("#20F57C00"))
            }
            "Energia" -> {
                viewBinding.category.setTextColor(Color.parseColor("#3E2723"))
                viewBinding.category.background.setTint(Color.parseColor("#203E2723"))
            }
            "Healthcare" -> {
                viewBinding.category.setTextColor(Color.parseColor("#212121"))
                viewBinding.category.background.setTint(Color.parseColor("#20212121"))
            }
            "Deuda_gubernamental" -> {
                viewBinding.category.text = "Deuda"
                viewBinding.category.setTextColor(Color.parseColor("#1B5E20"))
                viewBinding.category.background.setTint(Color.parseColor("#201B5E20"))
            }
            "Industrial" -> {
                viewBinding.category.setTextColor(Color.parseColor("#004D40"))
                viewBinding.category.background.setTint(Color.parseColor("#20004D40"))
            }
            "Criptomoneda" -> {
                viewBinding.category.setTextColor(Color.parseColor("#FF6F00"))
                viewBinding.category.background.setTint(Color.parseColor("#20FF6F00"))
            }
            else -> {
                viewBinding.category.setTextColor(Color.parseColor("#EE0000"))
                viewBinding.category.background.setTint(Color.parseColor("#20EE0000"))
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_available_investment
    }

    override fun initializeViewBinding(view: View): ItemAvailableInvestmentBinding {
        return ItemAvailableInvestmentBinding.bind(view)
    }
}