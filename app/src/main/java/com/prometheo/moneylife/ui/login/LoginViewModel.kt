package com.prometheo.moneylife.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.preferences.PrefsImp
import com.prometheo.moneylife.data.services.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        private val userService: UserService,
        private val prefs: PrefsImp
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>().apply {
        value = UiModel(showError = false, showLoading =  true, goToApp = false)
    }

    val uiModel: LiveData<UiModel> = _uiModel

    fun login(email: String, password: String) {
        _uiModel.value = _uiModel.value?.copy(showLoading =  true, showError = false)

        viewModelScope.launch {
            val response = userService.login(UserBody(email, password))

            if (response.isSuccessful && response.body()?.errorMessage.isNullOrBlank()) {
                prefs.userName = email
                prefs.password = password
                _uiModel.value = _uiModel.value?.copy(
                        showLoading =  false,
                        showError = false,
                        goToApp = true)

               // TODO - add stuff tu user prefs
            } else {
                _uiModel.value = _uiModel.value?.copy(showLoading = true, showError = true)
            }
        }
    }

    data class UiModel(
            val showError: Boolean,
            val showLoading: Boolean,
            val goToApp: Boolean
    )
}