package com.prometheo.moneylife.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sample (
    val sampleProperty: String
)
