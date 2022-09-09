package com.example.mercadolibre.data.domain.home

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import javax.inject.Inject

class InsertCurrencies @Inject constructor(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(currencies: List<CurrencyResponse>) {
        repository.insertCurrencies(currencies)
    }

}