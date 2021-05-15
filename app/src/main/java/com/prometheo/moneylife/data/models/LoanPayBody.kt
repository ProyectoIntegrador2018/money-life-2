package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoanPayBody(
    @field:Json(name = "UserID") val userId: Int,
    @field:Json(name = "PrestamoID") val loanId: Int,
    @field:Json(name = "Amortizacion") val amortization: Int
)