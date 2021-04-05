package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

enum class TurnActionCategory(val value: String) {
    @Json(name = "Inversion")
    INVESTMENT("Inversion"),

    @Json(name = "Diversion")
    FUN("Diversion"),

    @Json(name = "Bienes Personales")
    GOODS("Bienes Personales"),

    @Json(name = "Laboral")
    WORK("Laboral")
}