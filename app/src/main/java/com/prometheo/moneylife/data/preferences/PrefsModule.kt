package com.prometheo.moneylife.data.preferences

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
abstract class PrefsModule {
    @Binds
    abstract fun providePrefs(prefsImpl: PrefsImp): Prefs
}
