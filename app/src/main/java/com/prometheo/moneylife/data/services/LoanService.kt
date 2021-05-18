package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface LoanService {
    @POST("/prestamo/prestamosActuales/")
    suspend fun getUserLoans(@Body userIdBody: UserIdBody): Response<List<UserLoan>>

    @GET("/prestamo/catalogo")
    suspend fun getAvailableLoans(): Response<List<AvailableLoan>>

    @PUT("/prestamo/Amortizacion/")
    suspend fun payLoan(@Body loanPayBody: LoanPayBody): Response<Message>

    @PUT("/prestamo/Realizar/")
    suspend fun applyForLoan(@Body loanApplicationBody: LoanApplicationBody): Response<Message>
}