package com.prometheo.moneylife.ui.turn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.Turn
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.TurnService
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

    fun getTurnData () {
        viewModelScope.launch {
            try {
                val response = turnService.getTurnData ( UserIdBody(prefs.userId) )
                if (response.isSuccessful) {
                    _turnData.value = response.body()!![0]
                }
            } catch (err: Throwable) {
                //TODO: Error toast
            }
        }
    }

    fun nextTurn () {
        viewModelScope.launch {
            try {
                val response = turnService.nextTurn ( UserIdBody(prefs.userId) )
                if (response.isSuccessful) {
                    _turnData.value = response.body()!![0]
                }
            } catch (err: Throwable) {
                //TODO: Error toast
                println(err)
            }
        }
    }
}
