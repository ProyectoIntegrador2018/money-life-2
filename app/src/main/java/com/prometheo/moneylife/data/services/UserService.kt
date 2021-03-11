package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.UserBody
import com.prometheo.moneylife.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/users/")
    suspend fun register(@Body userBody: UserBody): Response<User>

    @POST("/users/login/")
    suspend fun login(@Body userBody: UserBody): Response<User>
}