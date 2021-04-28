package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableInvestment(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "Inversion") val name: String,
    @field:Json(name = "TipoInversion") val type: String,
    @field:Json(name = "RangoRendimiento") val range: String,
)