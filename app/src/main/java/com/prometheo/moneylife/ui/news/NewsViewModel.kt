package com.prometheo.moneylife.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.TurnEvent
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.room.AppDatabase
import com.prometheo.moneylife.data.room.TurnEventDao
import com.prometheo.moneylife.data.services.TurnService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    private val turnService: TurnService,
    private val prefs: Prefs,
    private val turnEventDao: TurnEventDao
) : ViewModel() {
    val turnEvents: LiveData<List<TurnEvent>> = turnEventDao.observeAll()
}