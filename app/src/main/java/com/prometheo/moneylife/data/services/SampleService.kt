package com.prometheo.moneylife.data.services

import com.prometheo.moneylife.data.models.Sample
import retrofit2.http.GET

interface SampleService {

    @GET("/sample")
    suspend fun getSample(): Sample
}