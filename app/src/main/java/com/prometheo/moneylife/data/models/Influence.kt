package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Influence(
        @field:Json(name = "Afecta") val influences: String,
        @field:Json(name = "Cantidad") val amount: String, //It can be an int or percentage
        @field:Json(name = "Periodo") val period: String,
        @field:Json(name = "Duracion") val duration: Any
)
