package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class Message (
    @field:Json(name = "mensaje") val message: String
)