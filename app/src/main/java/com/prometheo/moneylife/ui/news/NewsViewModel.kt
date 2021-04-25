package com.prometheo.moneylife.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.TurnEvent
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.room.AppDatabase
import com.prometheo.moneylife.data.services.TurnService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    private val turnService: TurnService,
    private val prefs: Prefs,
    private val db: AppDatabase
) : ViewModel() {

    val turnEvents: LiveData<List<TurnEvent>> = db.turnEventDao().observeAll()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    //TODO: Mover a TurnViewModel?
    fun updateTurnEvents () {

        _loading.value = true

        viewModelScope.launch {
            try {
                val response = turnService.getTurnEvents( UserIdBody( prefs.userId ) )
                if ( response.isSuccessful ) {
                    db.turnEventDao().insert( response.body()?.first()!! )
                }
            } catch ( err: Throwable ) {
                //TODO: Add error message
            }
            _loading.value = false
        }
    }

}