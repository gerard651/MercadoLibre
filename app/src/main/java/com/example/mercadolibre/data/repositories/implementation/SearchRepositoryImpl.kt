package com.example.mercadolibre.data.repositories.implementation

import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.entities.exceptions.InvalidSearchException
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
): SearchRepository {

    override suspend fun getSearchHistory(): Resource<List<SearchHistoryDb>> {
        return try {
            Resource.Success(searchHistoryDao.getSearchHistory())
        } catch(e: IOException) {
            return Resource.Error(Constants.ERROR_IO_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(Constants.ERROR_HTTP_EXCEPTION)
        }
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

    @Throws(InvalidSearchException::class)
    override suspend fun isValidInputSearch(searchText: String): Boolean {
        val isValid = searchText.length >= Constants.SEARCH_MIN_LENGTH
        if(!isValid)
            throw InvalidSearchException()
        return isValid
    }

    override suspend fun isValidSearch(searchText: String): Resource<Boolean> {
        return try {
            Resource.Success(isValidInputSearch(searchText))
        } catch (e: InvalidSearchException) {
            Resource.Error(e.message ?: "")
        }
    }

}