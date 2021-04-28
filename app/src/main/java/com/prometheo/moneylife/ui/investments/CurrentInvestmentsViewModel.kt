package com.prometheo.moneylife.ui.investments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.AvailableInvestment
import com.prometheo.moneylife.data.models.InvestmentTransactionBody
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.models.UserInvestment
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.InvestmentsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentInvestmentsViewModel @Inject constructor(
    private val investmentsService: InvestmentsService,
    private val userPrefs: Prefs,
) : ViewModel() {

    private val _state = MutableLiveData<UiModel>(
        UiModel(loading = false, error = false)
    )
    val state: LiveData<UiModel> = _state

    private val _currentInvestments = MutableLiveData<List<UserInvestment>>()
    val currentInvestments: LiveData<List<UserInvestment>> = _currentInvestments

    private fun updateUi(f: UiModel.() -> UiModel) =_state.postValue(f(_state.value!!))

    fun loadData() = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            val res = investmentsService.getCurrentInvestments(UserIdBody(userId = userPrefs.userId))
            _currentInvestments.value = res.body()!!

        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    fun invest(investmentId: Int, amount: Float) = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            val res = investmentsService.invest(InvestmentTransactionBody(
                investmentId = investmentId,
                userId = userPrefs.userId,
                amount = amount,
            ))
        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    fun withdraw(investmentId: Int, amount: Float) = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            val res = investmentsService.withdraw(InvestmentTransactionBody(
                investmentId = investmentId,
                userId = userPrefs.userId,
                amount = amount,
            ))
        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    data class UiModel(
        val loading: Boolean,
        val error: Boolean
    )
}