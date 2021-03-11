package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class TurnActionBody(
        @field:Json(name = "UserID") val userId: Int,
        @field:Json(name = "PreguntaID") val actionId: Int
)
