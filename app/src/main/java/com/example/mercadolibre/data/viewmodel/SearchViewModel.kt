package com.example.mercadolibre.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto
import com.example.mercadolibre.data.helpers.toListOfSearchDto
import com.example.mercadolibre.data.interfaces.IErrorLogger
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val errorLogger: IErrorLogger
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var searchHistory = MutableLiveData<ArrayList<SearchHistoryDto>>(arrayListOf())

    fun insertOrUpdateSearch(searchHistory: SearchHistoryDb) {
        viewModelScope.launch {
            repository.insertOrUpdateSearchHistory(searchHistory)
        }
    }

    fun updateSearch(searchText: String) {
        viewModelScope.launch {
            repository.updateSearchHistory(searchText, System.currentTimeMillis())
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            isLoading.postValue(true)
            when(val response = repository.getSearchHistory()) {
                is Resource.Success -> {
                    searchHistory.postValue(ArrayList(response.data?.toListOfSearchDto() ?: arrayListOf()))
                    isLoading.postValue(false)
                }
                is Resource.Error -> {
                    val errorMessage = response.message ?: ""
                    errorLogger.logError(errorMessage)
                    isLoading.postValue(false)
                    error.postValue(errorMessage)
                }
            }
        }
    }

}