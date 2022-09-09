package com.example.mercadolibre.data.domain.currency

import com.example.mercadolibre.data.entities.exceptions.CurrencyNotFoundException
import com.example.mercadolibre.data.repositories.interfaces.CurrencyRepository
import javax.inject.Inject

class GetCurrencySymbolById @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    @Throws(CurrencyNotFoundException::class)
    suspend operator fun invoke(currencyId: String): String {
        val symbol = currencyRepository.getCurrencyById(currencyId)
        if(symbol.isEmpty())
            throw CurrencyNotFoundException()
        return symbol
    }

}