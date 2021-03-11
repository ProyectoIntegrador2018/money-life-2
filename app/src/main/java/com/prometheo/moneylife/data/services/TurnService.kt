package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface TurnService {

    @POST("/pregunta/getPreguntas/")
    suspend fun getTurnActions(@Body userIdBody: UserIdBody): Response<List<TurnAction>>

    @PUT("/pregunta/afectaPreguntas/")
    suspend fun selectTurnActions(@Body turnActionBody: TurnActionBody): Response<List<Turn>>

    @POST("/turno/inicio/")
    suspend fun init(@Body userIdBody: UserIdBody): Response<List<Turn>>

    @POST("/turno/interemedio/")
    suspend fun refresh(@Body userIdBody: UserIdBody): Response<List<Turn>>

}