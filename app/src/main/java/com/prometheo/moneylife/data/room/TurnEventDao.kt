package com.prometheo.moneylife.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prometheo.moneylife.data.models.TurnEvent

@Dao
interface TurnEventDao {
    @Query("SELECT * FROM turnEvent")
    fun observeAll(): LiveData<List<TurnEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg turnEvent: TurnEvent)
}