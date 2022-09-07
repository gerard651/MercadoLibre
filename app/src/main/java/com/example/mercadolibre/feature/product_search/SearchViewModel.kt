package com.example.mercadolibre.feature.product_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.model.Product
import com.example.mercadolibre.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var products = MutableLiveData<List<Product>>(listOf())

    fun searchProduct(name: String) {
        viewModelScope.launch {
            isLoading.value = true
            when(val response = repository.getProductsByName(name)) {
                is Resource.Success -> {
                    response.data.let { searchResponse ->
                        searchResponse.let { productsResponse ->
                            products.value = productsResponse!!.results
                        }
                    }
                    isLoading.value = false
                }
                is Resource.Error -> {
                    error.value = response.message ?: ""
                    isLoading.value = false
                }
            }
        }
    }


}