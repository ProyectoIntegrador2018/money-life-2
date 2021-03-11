package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "id") val userId: Int,
    @field:Json(name = "username") val email: String
)
