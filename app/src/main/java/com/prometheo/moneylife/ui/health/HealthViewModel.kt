package com.prometheo.moneylife.ui.health

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.Happiness
import com.prometheo.moneylife.data.models.UserIdBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.HappinessService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val happinessService: HappinessService,
    private val prefs: Prefs
) : ViewModel() {
    private val _happinessData = MutableLiveData<List<Happiness>>()
    val happinessData: LiveData<List<Happiness>> = _happinessData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getHappinessData () {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = happinessService.getHappiness ( UserIdBody(prefs.userId) )
                if (response.isSuccessful) {
                    _happinessData.value = response.body()

                    _happinessData.value?.map { item ->
                        item.percentage = item.percentage.drop(1) + " %"
                    }
                }
            } catch (err: Throwable) {
                //TODO: Error toast
            }
            _loading.value = false
        }
    }
}