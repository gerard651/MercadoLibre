package com.example.mercadolibre.data.api

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import retrofit2.http.GET

interface CurrenciesApi {

    /**
     * Listado de monedas disponibles
     */
    @GET("currencies")
    suspend fun getCurrencies(): List<CurrencyResponse>

}