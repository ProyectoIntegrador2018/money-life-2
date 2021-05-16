package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableLoan(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "TipoPrestamo") val type: String,
    @field:Json(name = "Duracion") val duration: String,
    @field:Json(name = "TazaInteres") val interest_rate: String
)