package com.prometheo.moneylife.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "investmentRecord")
@JsonClass(generateAdapter = true)
data class UserInvestment(
    @PrimaryKey(autoGenerate = true) var roomDBId: Int?,
    @ColumnInfo(name = "turnNumber") var turnNumber: Int?,
    @ColumnInfo(name = "id") @field:Json(name = "id") var id: Int,
    @Ignore @field:Json(name = "NombreInversion") val name: String,
    @Ignore @field:Json(name = "TipoEmpresa") val type: String,
    @Ignore @field:Json(name = "SaldoInicial") val initialBalance: Float,
    @Ignore @field:Json(name = "Aportacion") val initialContribution: Float,
    @ColumnInfo(name = "investmentBalance") @field:Json(name = "SaldoActual") var currentBalance: Float
) {
    constructor():this(roomDBId=null, turnNumber=-1, id=-1, name="", type="", initialBalance = 0f, initialContribution = 0f, currentBalance = 0f)
}
