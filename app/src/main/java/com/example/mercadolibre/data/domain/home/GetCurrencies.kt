package com.example.mercadolibre.data.domain.home

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.exceptions.EmptyCurrenciesException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import javax.inject.Inject

class GetCurrencies @Inject constructor(
    private val repository: HomeRepository
) {

    @Throws(EmptyCurrenciesException::class)
    suspend operator fun invoke(): Resource<List<CurrencyResponse>> {
        val currencies = repository.getCurrencies()
        if(currencies.data.isNullOrEmpty())
            throw EmptyCurrenciesException()
        return currencies
    }

}