package com.example.mercadolibre.presentation.features.product_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto
import com.example.mercadolibre.data.entities.exceptions.InvalidSearchException
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
    var isValidSearch = MutableLiveData(false)
    var searchHistory = MutableLiveData<ArrayList<SearchHistoryDto>>(arrayListOf())
    var searchText = ""

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

    fun validateSearchText(textToValidate: String) {
        viewModelScope.launch {
            searchText = textToValidate
            when(val response = repository.isValidSearch(searchText)) {
                is Resource.Success -> {
                    isValidSearch.postValue(response.data ?: false)
                }
                is Resource.Error -> {
                    val errorMessage = response.message ?: ""
                    error.postValue(errorMessage)
                    errorLogger.logError(errorMessage)
                }
            }
        }
    }

    fun resetValidSearch() {
        isValidSearch.postValue(false)
        error.postValue("")
    }

}

