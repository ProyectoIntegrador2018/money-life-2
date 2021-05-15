package com.prometheo.moneylife.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prometheo.moneylife.data.models.TurnEvent
import com.prometheo.moneylife.data.room.TurnEventDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    private val turnEventDao: TurnEventDao
) : ViewModel() {
    val turnEvents: LiveData<List<TurnEvent>> = turnEventDao.observeAll()
}