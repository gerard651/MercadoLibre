package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.model.SearchResponse
import com.example.mercadolibre.utils.Resource

interface SearchRepository {

    suspend fun getProductsByName(productName: String): Resource<SearchResponse>

    suspend fun insertSearchHistory(searchHistory: SearchHistory)

    suspend fun insertOrUpdateSearchHistory(searchHistory: SearchHistory)

    suspend fun existSearch(searchText: String): Boolean

    suspend fun getSearchHistory(): Resource<List<SearchHistory>>

    suspend fun updateSearchHistory(searchText: String, timestamp: Long)

}