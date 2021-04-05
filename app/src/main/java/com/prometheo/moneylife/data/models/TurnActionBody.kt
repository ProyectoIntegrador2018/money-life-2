package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TurnActionBody(
        @field:Json(name = "UserID") val userId: Int,
        @field:Json(name = "PreguntaID") val actionId: Int
)
