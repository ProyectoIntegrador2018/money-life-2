package com.prometheo.moneylife.ui.turn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.*
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.TurnService
import com.prometheo.moneylife.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurnViewModel  @Inject constructor(
    private val turnService: TurnService,
    private val prefs: Prefs
) : ViewModel() {

    private val _turnData = MutableLiveData<Turn>()
    val turnData: LiveData<Turn> = _turnData

    private val _turnActions = MutableLiveData<List<TurnAction>>()
    val turnActions: LiveData<List<TurnAction>> = _turnActions

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val blockTurnActionCategory = SingleLiveEvent<TurnActionCategory>()
    fun getBlockTurnActionCategory(): SingleLiveEvent<TurnActionCategory> {
        return blockTurnActionCategory
    }

    private val message = SingleLiveEvent<String>()
    fun getMessage(): SingleLiveEvent<String> {
        return message
    }

    fun getTurnData () {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = turnService.getTurnData(UserIdBody(prefs.userId))
                if (response.isSuccessful) {
                    getTurnActions()
                    _turnData.value = response.body()?.first()
                }
            } catch (err: Throwable) {
                message.value = err.message
            }
        }
    }

    fun nextTurn() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = turnService.nextTurn(UserIdBody(prefs.userId))
                if (response.isSuccessful) {
                    getTurnActions()
                    _turnData.value = response.body()!![0]
                }
            } catch (err: Throwable) {
                message.value = err.message
            }
        }
    }

    private fun getTurnActions() {
        viewModelScope.launch {
            try {
                val response = turnService.getTurnActions(UserIdBody(prefs.userId))
                if (response.isSuccessful) {
                    _turnActions.value = response.body()
                }
            } catch (err: Throwable) {
                message.value = err.message
            }
            _loading.value = false
        }
    }

    fun selectTurnAction(actionId: Int, category: TurnActionCategory) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = turnService.selectTurnAction(TurnActionBody(prefs.userId, actionId))
                if (response.isSuccessful) {
                    _turnData.value = response.body()!![0]
                    blockTurnActionCategory.value = category
                }
            } catch (err: Throwable) {
                message.value = "No cuentas con los requisitos necesarios para esta acción"
            }
            _loading.value = false
        }
    }
}
