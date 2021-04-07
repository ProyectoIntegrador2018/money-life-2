package com.prometheo.moneylife.data.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object ServicesModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().readTimeout(25, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://moneylifev1.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

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
