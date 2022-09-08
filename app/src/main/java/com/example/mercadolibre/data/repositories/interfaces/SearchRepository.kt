package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.database.SearchHistoryDb

interface SearchRepository {

    suspend fun insertSearchHistory(searchHistory: SearchHistoryDb)

    suspend fun insertOrUpdateSearchHistory(searchHistory: SearchHistoryDb)

    suspend fun existSearch(searchText: String): Boolean

    suspend fun getSearchHistory(): Resource<List<SearchHistoryDb>>

    suspend fun updateSearchHistory(searchText: String, timestamp: Long)

}