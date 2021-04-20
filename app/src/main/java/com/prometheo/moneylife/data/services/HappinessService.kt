package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HappinessService {

    @POST("/turno/felicidad/")
    suspend fun getHappiness(@Body userIdBody: UserIdBody): Response<List<Happiness>>
}