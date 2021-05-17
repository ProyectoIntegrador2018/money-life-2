package com.prometheo.moneylife.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prometheo.moneylife.data.models.TurnEvent
import com.prometheo.moneylife.data.models.UserInvestment

@Database(entities = [TurnEvent::class, UserInvestment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun turnEventDao(): TurnEventDao
    abstract fun investmentRecordDao(): InvestmentRecordDao
}