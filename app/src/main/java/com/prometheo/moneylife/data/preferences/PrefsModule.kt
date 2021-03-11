package com.prometheo.moneylife.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
abstract class PrefsModule {
    @Binds
    abstract fun providePrefs(prefsImpl: PrefsImp): Prefs
}