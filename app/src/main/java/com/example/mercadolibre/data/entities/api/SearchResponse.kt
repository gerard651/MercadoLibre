package com.example.mercadolibre.data.entities.api

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results") val results: List<ProductResponse> = listOf()
)
