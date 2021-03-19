package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.models.User
import com.prometheo.moneylife.data.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/users/")
    suspend fun register(@Body userBody: UserBody): Response<LoginResponse>

    @POST("/users/login/")
    suspend fun login(@Body userBody: UserBody): Response<LoginResponse>
}