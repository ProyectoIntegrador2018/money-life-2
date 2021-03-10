package com.prometheo.moneylife.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.services.SampleService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val sampleService: SampleService
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    init {
        viewModelScope.launch {
            doSample()
        }
    }

    private suspend fun doSample() {
        try {
            val sample = sampleService.getSample()
            _text.value = sample.sampleProperty
        } catch (ex: HttpException) {
            _text.value = "ha ocurrido un error"
        }
    }
}
