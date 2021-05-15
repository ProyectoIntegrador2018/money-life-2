package com.prometheo.moneylife.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "investmentRecord",primaryKeys = arrayOf("turnNumber","id"))
@JsonClass(generateAdapter = true)
data class InvestmentRecord(
    @ColumnInfo(name = "turnNumber") var turnNumber: Int?,
    @ColumnInfo(name = "id") @field:Json(name = "id") var id: Int?,
    @Ignore @field:Json(name = "NombreInversion") val name: String?,
    @Ignore @field:Json(name = "TipoEmpresa") val type: String?,
    @Ignore @field:Json(name = "SaldoInicial") val initialBalance: Float?,
    @Ignore @field:Json(name = "Aportacion") val initialContribution: Float?,
    @ColumnInfo(name = "investmentBalance") @field:Json(name = "SaldoActual") var investmentBalance: Float?
) {
    constructor():this(turnNumber=null, id=null, name=null, type=null, initialBalance = null, initialContribution = null, investmentBalance = null)
}