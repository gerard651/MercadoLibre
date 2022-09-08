package com.example.mercadolibre.data.api

import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.api.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("items/{itemId}")
    suspend fun getProductDetail(
        @Path("itemId") itemId: String
    ): ProductDetailResponse

}