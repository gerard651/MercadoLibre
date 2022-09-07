package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.model.SearchResponse
import com.example.mercadolibre.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi
): SearchRepository {

    override suspend fun getProductsByName(productName: String): Resource<SearchResponse> {
        val response = try {
            api.getProductsByName(productName)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

}