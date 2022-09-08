package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.api.CurrenciesApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.helpers.toListOfCurrency
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val currenciesApi: CurrenciesApi,
    private val currenciesDao: CurrenciesDao,
): HomeRepository {

    override suspend fun getCurrencies(): Resource<List<CurrencyResponse>> {
        val response = try {
            currenciesApi.getCurrencies()
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

    override suspend fun insertCurrencies(currencies: List<CurrencyResponse>) {
        currenciesDao.insertCurrencies(currencies.toListOfCurrency())
    }

}