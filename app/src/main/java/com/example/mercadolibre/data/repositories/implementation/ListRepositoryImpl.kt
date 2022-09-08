package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.api.SearchApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val currenciesDao: CurrenciesDao
): ListRepository {

    override suspend fun getCurrencyById(id: String): String {
        return currenciesDao.getCurrencySymbolById(id)
    }

    override suspend fun getProductsByName(productName: String): Resource<SearchResponse> {
        return try {
            Resource.Success(searchApi.getProductsByName(productName))
        } catch(e: IOException) {
            return Resource.Error(Constants.ERROR_IO_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(Constants.ERROR_HTTP_EXCEPTION)
        }
    }

}