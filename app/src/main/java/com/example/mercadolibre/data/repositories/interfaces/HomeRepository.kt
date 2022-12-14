package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.helpers.Resource

interface HomeRepository {

    suspend fun getCurrencies(): Resource<List<CurrencyResponse>>

    suspend fun insertCurrencies(currencies: List<CurrencyResponse>)

}