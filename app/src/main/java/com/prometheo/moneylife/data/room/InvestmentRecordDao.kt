package com.prometheo.moneylife.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prometheo.moneylife.data.models.UserInvestment

@Dao
interface InvestmentRecordDao {
    @Query("SELECT * FROM investmentRecord ORDER BY turnNumber")
    fun observeAll(): LiveData<List<UserInvestment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg UserInvestment: UserInvestment)
}