package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("sites/$SITE_UY/search")
    suspend fun getProductsByName(
        @Query("q") productName: String
    ): SearchResponse

    companion object {
        private const val SITE_UY = "MLU"
    }

}