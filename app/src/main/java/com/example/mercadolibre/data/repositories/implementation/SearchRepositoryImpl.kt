package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
): SearchRepository {

    override suspend fun getSearchHistory(): Resource<List<SearchHistoryDb>> {
        val response = try {
            searchHistoryDao.getSearchHistory()
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

    override suspend fun insertSearchHistory(searchHistory: SearchHistoryDb) {
        searchHistoryDao.insertSearch(searchHistory)
    }

    override suspend fun insertOrUpdateSearchHistory(searchHistory: SearchHistoryDb) {
        if(!existSearch(searchHistory.text))
            insertSearchHistory(searchHistory)
        else
            updateSearchHistory(searchHistory.text, searchHistory.timestamp)
    }

    override suspend fun existSearch(searchText: String): Boolean {
        return searchHistoryDao.existSearchInHistory(searchText)
    }

    override suspend fun updateSearchHistory(searchText: String, timestamp: Long) {
        searchHistoryDao.updateSearchHistoryByText(searchText, timestamp)
    }

}