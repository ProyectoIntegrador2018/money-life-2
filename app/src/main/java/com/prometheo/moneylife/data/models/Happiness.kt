package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Happiness(
    @field:Json(name = "Decripcion") val description: String,
    @field:Json(name = "Cantidad") val percentage: String,
    @field:Json(name = "Periodo") val period: String,
    @field:Json(name = "Duracion") val duration: String,
)