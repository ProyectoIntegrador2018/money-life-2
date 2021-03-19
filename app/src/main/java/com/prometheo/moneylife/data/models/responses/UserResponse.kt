package com.prometheo.moneylife.data.models.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @field:Json(name = "id") val userId: Int?,
    @field:Json(name = "username") val email: String?,
    @field:Json(name = "mensaje") val errorMessage: String?
)