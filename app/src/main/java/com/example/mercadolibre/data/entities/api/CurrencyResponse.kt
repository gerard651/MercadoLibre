package com.example.mercadolibre.data.entities.api

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class CurrencyResponse @Inject constructor(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String
)