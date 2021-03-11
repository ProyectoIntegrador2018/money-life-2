package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class UserIdBody(
        @field:Json(name = "UserID") val userId: Int
)
