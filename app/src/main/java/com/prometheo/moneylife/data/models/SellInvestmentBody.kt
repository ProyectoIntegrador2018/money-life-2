package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SellInvestmentBody(
    @field:Json(name = "UserID") val userId: Int,
    @field:Json(name = "InversionID") val investmentId: Int,
)