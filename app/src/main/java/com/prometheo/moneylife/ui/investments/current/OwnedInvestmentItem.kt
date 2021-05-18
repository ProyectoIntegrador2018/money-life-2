package com.prometheo.moneylife.ui.investments.current

import android.graphics.Color
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
    private val onSellListener: () -> Unit = {},
    private val onShowGraphListener: (investmentId: Int) -> Unit = {}
) : BindableItem<ItemOwnedInvestmentBinding>() {
    private lateinit var _viewBinding: ItemOwnedInvestmentBinding
    private var editable = false

    override fun bind(viewBinding: ItemOwnedInvestmentBinding, position: Int) {
        _viewBinding = viewBinding

        viewBinding.investmentName.text = investmentName
        viewBinding.currentBalance.text = viewBinding.root.context.getString(
            R.string.tv_money, currentBalance)
        viewBinding.initialBalance.text = viewBinding.root.context.getString(
            R.string.tv_money, initialBalance)
        viewBinding.initialContribution.text = viewBinding.root.context.getString(
            R.string.tv_money, initialContribution)
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

        _viewBinding.investButton.isVisible = false
        _viewBinding.withdrawButton.isVisible = false
        _viewBinding.withdrawAllButton.isVisible = false
        _viewBinding.amountFieldHint.isVisible = false

        viewBinding.investmentItemCard.setOnClickListener { onShowGraphListener(id) }
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
