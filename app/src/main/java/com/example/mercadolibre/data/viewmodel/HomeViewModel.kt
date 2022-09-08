package com.example.mercadolibre.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.interfaces.IErrorLogger
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val errorLogger: IErrorLogger
): ViewModel() {
    var error = MutableLiveData("")

    fun getCurrencies() {
        viewModelScope.launch {
            when(val response = repository.getCurrencies()) {
                is Resource.Success -> {
                    insertCurrencies(response.data ?: listOf())
                }
                is Resource.Error -> {
                    val errorMessage = response.message ?: ""
                    errorLogger.logError(errorMessage)
                    error.postValue(errorMessage)
                }
            }
        }
    }

    private suspend fun insertCurrencies(currencies: List<CurrencyResponse>) {
        repository.insertCurrencies(currencies)
    }

}