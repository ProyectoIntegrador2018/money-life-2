package com.prometheo.moneylife.data.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object ServicesModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://moneylifev1.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideSampleService(
        retrofit: Retrofit
    ): SampleService = retrofit.create(SampleService::class.java)

    @Provides
    fun provideUserService(
            retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    fun provideTurnService(
            retrofit: Retrofit
    ): TurnService = retrofit.create(TurnService::class.java)
}
