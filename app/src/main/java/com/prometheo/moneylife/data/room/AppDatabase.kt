package com.prometheo.moneylife.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prometheo.moneylife.data.models.TurnEvent

@Database(entities = [TurnEvent::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun turnEventDao(): TurnEventDao
}