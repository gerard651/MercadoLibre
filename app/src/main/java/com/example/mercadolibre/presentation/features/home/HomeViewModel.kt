package com.example.mercadolibre.presentation.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.domain.home.HomeUseCases
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.exceptions.EmptyCurrenciesException
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
import com.example.mercadolibre.data.helpers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    private val errorLogger: IErrorLogger
): ViewModel() {
    var error = MutableLiveData("")

    fun getCurrencies() {
        viewModelScope.launch {
            try {
                when(val response = homeUseCases.getCurrenciesUseCase()) {
                    is Resource.Success -> {
                        insertCurrencies(response.data ?: listOf())
                    }
                    is Resource.Error -> {
                        val errorMessage = response.message
                        errorLogger.logError(errorMessage)
                        error.postValue(errorMessage)
                    }
                }
            } catch (e: EmptyCurrenciesException) {
                errorLogger.logError(e.message)
                error.postValue(e.message)
            }

        }
    }

    private suspend fun insertCurrencies(currencies: List<CurrencyResponse>) {
        homeUseCases.insertCurrencies(currencies)
    }

}