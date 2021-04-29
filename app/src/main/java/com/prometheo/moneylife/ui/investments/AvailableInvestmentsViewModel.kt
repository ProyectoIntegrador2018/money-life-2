package com.prometheo.moneylife.ui.investments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.AvailableInvestment
import com.prometheo.moneylife.data.models.InvestmentTransactionBody
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.InvestmentsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableInvestmentsViewModel @Inject constructor(
    private val investmentsService: InvestmentsService,
    private val userPrefs: Prefs,
) : ViewModel() {

    private val _state = MutableLiveData<UiModel>(
        UiModel(loading = false, error = false, transactionComplete = false)
    )
    val state: LiveData<UiModel> = _state

    private val _availableInvestments = MutableLiveData<List<AvailableInvestment>>()
    val availableInvestments: LiveData<List<AvailableInvestment>> = _availableInvestments

    private fun updateUi(f: UiModel.() -> UiModel) =_state.postValue(f(_state.value!!))

    fun loadData() = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            val res = investmentsService.getAvailableInvestments(UserIdBody(userId = userPrefs.userId))
            _availableInvestments.value = res.body()!!

        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    fun newInvestment(investmentId: Int, amount: Int) = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            investmentsService.newInvestment(
                InvestmentTransactionBody(userId = userPrefs.userId, investmentId = investmentId, amount = amount)
            )

            updateUi { copy(loading = false, transactionComplete = true) }

        } catch (err: Throwable) {
            updateUi { copy(error = true, loading = false) }
        }
    }


    data class UiModel(
        val loading: Boolean,
        val error: Boolean,
        val transactionComplete: Boolean
    )
}