package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.repositories.interfaces.CurrencyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val currenciesDao: CurrenciesDao
): CurrencyRepository {

    override suspend fun getCurrencyById(id: String): String {
        return currenciesDao.getCurrencySymbolById(id)
    }

}