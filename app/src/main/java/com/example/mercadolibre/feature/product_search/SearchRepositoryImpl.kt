package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.bd.SearchHistoryDao
import com.example.mercadolibre.model.SearchResponse
import com.example.mercadolibre.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi,
    private val searchHistoryDao: SearchHistoryDao
): SearchRepository {

    override suspend fun getProductsByName(productName: String): Resource<SearchResponse> {
        val response = try {
            api.getProductsByName(productName)
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

    override suspend fun getSearchHistory(): Resource<List<SearchHistory>> {
        val response = try {
            searchHistoryDao.getSearchHistory()
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "")
        }
        return Resource.Success(response)
    }

    override suspend fun insertSearchHistory(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearch(searchHistory)
    }

    override suspend fun insertOrUpdateSearchHistory(searchHistory: SearchHistory) {
        if(!existSearch(searchHistory.text))
            insertSearchHistory(searchHistory)
        else
            updateSearchHistory(searchHistory.text, searchHistory.timestamp)
    }

    override suspend fun existSearch(searchText: String): Boolean {
        val exist = searchHistoryDao.existSearchInHistory(searchText)
        return exist
    }

    override suspend fun updateSearchHistory(searchText: String, timestamp: Long) {
        searchHistoryDao.updateSearchHistoryByText(searchText, timestamp)
    }

}