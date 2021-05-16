package com.prometheo.moneylife.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prometheo.moneylife.data.models.UserInvestment

@Dao
interface InvestmentRecordDao {
    @Query("SELECT * FROM investmentRecord WHERE id = :investmentId  ORDER BY turnNumber ASC")
    fun observeById(investmentId: Int): LiveData<List<UserInvestment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(userInvestments: List<UserInvestment>)
}