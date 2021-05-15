package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoan(
    @field:Json(name = "PrestamoID") val id: Int,
    @field:Json(name = "TipoPrestamo") val type: String,
    @field:Json(name = "Mensualidad") val monthly_payment: String,
    @field:Json(name = "SaldoAbsoluto") val balance: String,
    @field:Json(name = "MesesRestantes") val months_remaining: Int
)