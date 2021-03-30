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
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userService: UserService,
    private val prefs: Prefs
) : ViewModel() {
    data class UiModel(
        val showError: Boolean,
        val showLoading: Boolean,
        val goToApp: Boolean,
        val goToSignup: Boolean
    )

    private val _uiModel = MutableLiveData<UiModel>().apply {
        value = UiModel(showError = false, showLoading = false, goToApp = false, goToSignup = false)
    }

    val uiModel: LiveData<UiModel> = _uiModel

    fun login(email: String, password: String) {
        _uiModel.value = _uiModel.value?.copy(showLoading = true, showError = false)

        viewModelScope.launch {
            try {
                val response = userService.login(UserBody(email, password))

                if (response.isSuccessful && response.body()?.errorMessage.isNullOrBlank()) {
                    prefs.userId = response.body()!!.userId!!
                    prefs.userName = email
                    prefs.password = password

                    _uiModel.value = _uiModel.value?.copy(
                        showLoading = false,
                        showError = false,
                        goToApp = true
                    )
                } else {
                    _uiModel.postValue(uiModel.value?.copy(showLoading = false, showError = true))
                }
            } catch (err: Throwable) {
                _uiModel.postValue(uiModel.value?.copy(showLoading = false, showError = true))
            }
        }
    }

    fun goToSignup() {
        _uiModel.value = _uiModel.value?.copy(goToSignup = true)
    }
}