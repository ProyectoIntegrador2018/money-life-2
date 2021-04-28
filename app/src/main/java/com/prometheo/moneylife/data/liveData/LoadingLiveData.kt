package com.prometheo.moneylife.data.liveData

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoadingLiveData ()  : LiveData<Boolean>() {
    private val _loading = MutableLiveData<Boolean> ()
    val loading: LiveData<Boolean> = _loading

    fun setLoading (state: Boolean) {
        _loading.value = state
    }

    companion object {
        private lateinit var sInstance: LoadingLiveData

        @MainThread
        fun get(): LoadingLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else LoadingLiveData()
            return sInstance
        }
    }
}