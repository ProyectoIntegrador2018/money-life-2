package com.prometheo.moneylife.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isTrue
import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.models.responses.LoginResponse
import com.prometheo.moneylife.data.preferences.Prefs
import com.prometheo.moneylife.data.services.UserService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class LoginViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var mockUserService: UserService

    @RelaxedMockK
    private lateinit var mockPrefs: Prefs


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `Clicking login should show login indicator`() {
        // Arrange
        val email = "test@test.com"
        val password = "test"
        val userId = 1
        val vm = SignupViewModel(userService = mockUserService, prefs = mockPrefs)

        coEvery {
            mockUserService.login(UserBody(email = email, password = password))
        } returns Response.success(
            LoginResponse(userId = userId, email = email, errorMessage = null)
        )

        val updates = mutableListOf<SignupViewModel.UiModel>()
        vm.uiModel.observeForever { updates.add(it) }

        // Act
        vm.signup(email, password)

        // Assert
        updates.removeAt(0) // ignore initial value
        assert(updates.first().showLoading)
    }

    @Test
    fun `A valid login attempt should redirect the user to the main app`() {
        // Arrange
        val email = "test@test.com"
        val password = "test"
        val userId = 1
        val vm = SignupViewModel(userService = mockUserService, prefs = mockPrefs)

        coEvery {
            mockUserService.login(UserBody(email = email, password = password))
        } returns Response.success(
            LoginResponse(userId = userId, email = email, errorMessage = null)
        )

        val updates = mutableListOf<SignupViewModel.UiModel>()
        vm.uiModel.observeForever { updates.add(it) }

        // Act
        vm.signup(email, password)

        // Assert
        assertThat(updates.last().goToApp).isTrue()
    }

    @Test
    fun `A login attempt with invalid credentials should show the error message`() {
        // Arrange
        val email = "test@test.com"
        val password = "wrong password"
        val userId = 1
        val vm = SignupViewModel(userService = mockUserService, prefs = mockPrefs)

        coEvery {
            mockUserService.login(UserBody(email = email, password = password))
        } returns Response.success(
            LoginResponse(userId = null, email = null, errorMessage = "error")
        )

        val updates = mutableListOf<SignupViewModel.UiModel>()
        vm.uiModel.observeForever { updates.add(it) }

        // Act
        vm.signup(email, password)

        // Assert
        assertThat(updates.last().showError).isTrue()
    }
}