package com.prometheo.moneylife

import com.prometheo.moneylife.data.models.User
import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.services.UserService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class UserServiceUnitTest {

    @Mock
    private lateinit var mockUserService: UserService

    private val mockUser = User(USER_ID, EMAIL)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `user login is successful`() = runBlocking {

        Mockito.`when`(mockUserService.login(UserBody(EMAIL, PASSWORD)))
            .thenReturn(Response.success(mockUser))

        val result = mockUserService.login(UserBody(EMAIL, PASSWORD))

        assert(result.body() == mockUser)
    }

    @Test
    fun `user register is successful`() = runBlocking {
        Mockito.`when`(mockUserService.register(UserBody(EMAIL, PASSWORD)))
            .thenReturn(Response.success(mockUser))

        val result = mockUserService.register(UserBody(EMAIL, PASSWORD))

        assert(result.body() == mockUser)
    }

    companion object {
        private const val USER_ID = 32
        private const val EMAIL = "test@gmail.com"
        private const val PASSWORD = "123456"
    }
}