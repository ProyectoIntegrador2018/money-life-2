package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.Turn
import com.prometheo.moneylife.data.models.TurnAction
import com.prometheo.moneylife.data.models.TurnActionBody
import com.prometheo.moneylife.data.models.UserIdBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface TurnService {

    @POST("/pregunta/getPreguntas/")
    suspend fun getTurnActions(@Body userIdBody: UserIdBody): Response<List<TurnAction>>

    @PUT("/pregunta/afectaPreguntas/")
    suspend fun selectTurnActions(@Body turnActionBody: TurnActionBody): Response<List<Turn>>
}