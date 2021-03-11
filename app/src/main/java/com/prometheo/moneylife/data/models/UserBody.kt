package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class UserBody(
    @field:Json(name = "username") val email: String,
    @field:Json(name = "password") val password: String
)
