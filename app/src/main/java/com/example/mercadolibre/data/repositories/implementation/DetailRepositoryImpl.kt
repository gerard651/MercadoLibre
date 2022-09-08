package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val currenciesDao: CurrenciesDao
): DetailRepository {

    override suspend fun getCurrencyById(id: String): String {
        return currenciesDao.getCurrencySymbolById(id)
    }

    override suspend fun getProductDetailById(id: String): Resource<ProductDetailResponse> {
        val response = try {
            productApi.getProductDetail(id)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

}