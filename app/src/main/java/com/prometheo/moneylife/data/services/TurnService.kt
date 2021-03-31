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
    suspend fun nextTurn(@Body userIdBody: UserIdBody): Response<List<Turn>>

    @POST("/turno/intermedio/")
    suspend fun getTurnData(@Body userIdBody: UserIdBody): Response<List<Turn>>

    @PUT("/terminar/juego/")
    suspend fun endGame(@Body userIdBody: UserIdBody): Response<Message>
}