package com.prometheo.moneylife.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prometheo.moneylife.data.models.InvestmentRecord
import com.prometheo.moneylife.data.models.TurnEvent

@Database(entities = [TurnEvent::class, InvestmentRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun turnEventDao(): TurnEventDao
    abstract fun InvestmentRecordDao(): InvestmentRecordDao
}