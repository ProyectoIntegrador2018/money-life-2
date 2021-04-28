package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT


interface InvestmentsService {
    @POST("/inversion/catalogoDisponibles/")
    suspend fun getAvailableInvestments(@Body userIdBody: UserIdBody): Response<List<AvailableInvestment>>

    @POST("/inversion/nueva/")
    suspend fun newInvestment(@Body body: InvestmentTransactionBody): Response<Message>

    @POST("/inversion/inversionesActuales/")
    suspend fun getCurrentInvestments(@Body userIdBody: UserIdBody): Response<List<UserInvestment>>

    @PUT("/inversion/agregarDinero/")
    suspend fun invest(@Body body: InvestmentTransactionBody): Response<Message>

    @PUT("/inversion/retirarDinero/")
    suspend fun withdraw(@Body body: InvestmentTransactionBody): Response<Message>
}