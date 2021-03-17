package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class Turn(
        @field:Json(name = "id") val turnId: Int,
        @field:Json(name = "NumeroTurnos") val turnNumber: Int,
        @field:Json(name = "Felicidad") val happiness: Float,
        @field:Json(name = "DineroEfectivo") val balance: Float,
        @field:Json(name = "Ingresos") val income: Float,
        @field:Json(name = "Egresos") val expenses: Float,
        @field:Json(name = "Sueldo") val salary: Float
)
