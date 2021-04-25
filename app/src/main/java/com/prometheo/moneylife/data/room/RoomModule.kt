package com.prometheo.moneylife.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context : Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app-database"
        ).build()

    @Singleton
    @Provides
    fun provideTurnEventDao (db: AppDatabase) =
        db.turnEventDao()
}