package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.core.Constants.ERROR_HTTP_EXCEPTION
import com.example.mercadolibre.core.Constants.ERROR_IO_EXCEPTION
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.api.CurrenciesApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.helpers.toListOfCurrency
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val currenciesApi: CurrenciesApi,
    private val currenciesDao: CurrenciesDao,
): HomeRepository {

    override suspend fun getCurrencies(): Resource<List<CurrencyResponse>> {
        return try {
            Resource.Success(currenciesApi.getCurrencies())
        } catch(e: IOException) {
            return Resource.Error(ERROR_IO_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(ERROR_HTTP_EXCEPTION)
        }
    }

    override suspend fun insertCurrencies(currencies: List<CurrencyResponse>) {
        currenciesDao.insertCurrencies(currencies.toListOfCurrency())
    }

}