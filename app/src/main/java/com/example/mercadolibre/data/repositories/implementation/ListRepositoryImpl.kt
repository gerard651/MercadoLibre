package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.api.SearchApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
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
        val response = try {
            searchApi.getProductsByName(productName)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

}