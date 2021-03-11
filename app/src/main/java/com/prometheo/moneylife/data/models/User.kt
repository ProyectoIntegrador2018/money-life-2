package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "id") val userId: Int?,
    @field:Json(name = "username") val email: String?,
    @field:Json(name = "message") val errorMessage: String?
)
