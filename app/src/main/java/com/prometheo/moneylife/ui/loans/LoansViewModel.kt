package com.prometheo.moneylife.ui.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.*
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.LoanService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoansViewModel @Inject constructor(
    private val loanService: LoanService,
    private val userPrefs: Prefs,
) : ViewModel() {

    private val _state = MutableLiveData<UiModel>(
        UiModel(loading = false, error = false)
    )
    private val _loans = MutableLiveData<Loans>(
        Loans(userLoans = emptyList(), availableLoans = emptyList())
    )
    val state: LiveData<UiModel> = _state
    val loans: LiveData<Loans> = _loans


    private fun updateUi(f: UiModel.() -> UiModel) = _state.postValue(f(_state.value!!))

    fun loadData() = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            val userLoans = loanService.getUserLoans(UserIdBody(userId = userPrefs.userId)).body()!!
            val availableLoans = loanService.getAvailableLoans().body()!!
            _loans.postValue(Loans(userLoans = userLoans, availableLoans = availableLoans))

        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    fun applyForInvestment(
        loanId: Int,
        deposit: Int,
        totalValue: Int
    ) = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            loanService.applyForLoan(
                LoanApplicationBody(
                    userId = userPrefs.userId,
                    loanId = loanId,
                    deposit = deposit,
                    totalValue = totalValue
                )
            )

            loadData()

        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    fun payInvestment(loanId: Int, amortization: Int) = viewModelScope.launch {
        updateUi { copy(loading = true) }

        try {
            loanService.payLoan(
                LoanPayBody(
                    userId = userPrefs.userId,
                    loanId = loanId,
                    amortization = amortization
                )
            )

            loadData()

        } catch (err: Throwable) {
            updateUi { copy(error = true) }
        }

        updateUi { copy(loading = false) }
    }

    data class UiModel(
        val loading: Boolean,
        val error: Boolean
    )

    data class Loans(
        val userLoans: List<UserLoan>,
        val availableLoans: List<AvailableLoan>
    )
}