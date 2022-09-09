package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.entities.database.SearchHistoryEntity
import com.example.mercadolibre.data.helpers.Resource

interface SearchRepository {

    suspend fun insertSearchHistory(searchHistory: SearchHistoryEntity)

    suspend fun insertOrUpdateSearchHistory(searchHistory: SearchHistoryEntity)

    suspend fun existSearch(searchText: String): Boolean

    suspend fun getSearchHistory(): Resource<List<SearchHistoryEntity>>

    suspend fun updateSearchHistory(searchText: String, timestamp: Long)

    suspend fun isValidInputSearch(searchText: String): Boolean

    suspend fun isValidSearch(searchText: String): Resource<Boolean>

}