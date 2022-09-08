package com.example.mercadolibre.data.api

import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.entities.api.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    /**
     * Obtiene la lista de productos disponibles con el nombre ingresado
     * @param productName nombre de producto a buscar
     */
    @GET("sites/${Constants.SITE_UY}/search")
    suspend fun getProductsByName(
        @Query("q") productName: String
    ): SearchResponse

}