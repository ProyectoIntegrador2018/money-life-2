package com.prometheo.moneylife.ui.turn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.liveData.LoadingLiveData
import com.prometheo.moneylife.data.models.*
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.room.TurnEventDao
import com.prometheo.moneylife.data.services.TurnService
import com.prometheo.moneylife.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TurnViewModel  @Inject constructor(
    private val turnService: TurnService,
    private val prefs: Prefs,
    private val turnEventDao: TurnEventDao
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
                    updateTurnEvents()
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

    private fun updateTurnEvents () {
        _loading.value = true
        LoadingLiveData.get().setLoading ( true )

        viewModelScope.launch {
            try {
                val response = turnService.getTurnEvents( UserIdBody( prefs.userId ) )
                if ( response.isSuccessful ) {
                    /* val listNews = listOf<TurnEvent>(
                        TurnEvent(5, 10, "Prueba 1", "Buenas", listOf( Influence("aja", "12", "Semanal", 5) )),
                        TurnEvent(10, 15, "Prueba 2", "Buenas", listOf( Influence("aja", "12", "Semanal", 5) ))) */
                    val newsList = response.body()
                    for (news in newsList!!) {
                        news.turnNumber = turnData.value?.turnNumber
                    }
                    withContext (Dispatchers.IO) {
                        turnEventDao.insertAll( newsList )
                    }
                }
            } catch ( err: Throwable ) {
                message.value = "Error al cargar noticias"
            }
            LoadingLiveData.get().setLoading ( false )
            _loading.value = false
        }
    }
}
