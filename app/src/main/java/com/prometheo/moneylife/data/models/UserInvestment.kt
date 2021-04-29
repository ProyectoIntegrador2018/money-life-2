package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInvestment(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "NombreInversion") val name: String,
    @field:Json(name = "TipoEmpresa") val type: String,
    @field:Json(name = "SaldoInicial") val initialBalance: Float,
    @field:Json(name = "Aportacion") val initialContribution: Float,
    @field:Json(name = "SaldoActual") val currentBalance: Float
)
