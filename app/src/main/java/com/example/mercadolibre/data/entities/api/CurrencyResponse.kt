package com.example.mercadolibre.data.entities.api

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String
)