package com.prometheo.moneylife.data.models

import com.squareup.moshi.Json

data class TurnAction(
        @field:Json(name = "Pregunta_id") val actionId: Int,
        @field:Json(name = "Descripcion") val description: String,
        @field:Json(name = "TipoPregunta") val category: String,
        @field:Json(name = "Afecta") val influences: List<Influence>
)
