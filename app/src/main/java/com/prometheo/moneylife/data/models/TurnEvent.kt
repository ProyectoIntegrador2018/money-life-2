package com.prometheo.moneylife.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "turnEvent")
@JsonClass(generateAdapter = true)
data class TurnEvent(
    @PrimaryKey(autoGenerate = true) var _id: Int?,
    @Ignore @field:Json(name = "Evento_id") var eventId: Int?,
    @ColumnInfo(name = "turnNumber") var turnNumber: Int?,
    @ColumnInfo(name = "description") @field:Json(name = "Descripcion") var description: String?,
    @Ignore @field:Json(name = "TipoEvento") var type: String?,
    @Ignore @field:Json(name = "Afecta") val influences: List<Influence>?
) {
    constructor():this(_id=null, eventId=null, turnNumber=null, description=null, type=null, influences=null)
}
