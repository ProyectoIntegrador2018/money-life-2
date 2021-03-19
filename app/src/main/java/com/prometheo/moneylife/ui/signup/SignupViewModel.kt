package com.prometheo.moneylife.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.models.responses.LoginResponse
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.preferences.PrefsImp
import com.prometheo.moneylife.data.services.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userService: UserService,
    private val prefs: Prefs
) : ViewModel() {
    data class UiModel(
        val showError: Boolean,
        val showLoading: Boolean,
        val goToApp: Boolean,
        val goToLogin: Boolean
    )

    private val _uiModel = MutableLiveData<UiModel>().apply {
        value = UiModel(showError = false, showLoading = false, goToApp = false, goToLogin = false)
    }

    val uiModel: LiveData<UiModel> = _uiModel

    fun signup(email: String, password: String) {
        _uiModel.value = _uiModel.value?.copy(showLoading = true, showError = false)

        viewModelScope.launch {
            val response: Response<LoginResponse>
            try {
                response = userService.register(UserBody(email, password))

                if (response.isSuccessful && response.body()?.errorMessage.isNullOrBlank()) {
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

    fun goToLogin() {
        _uiModel.value = _uiModel.value?.copy(goToLogin = true)
    }
}