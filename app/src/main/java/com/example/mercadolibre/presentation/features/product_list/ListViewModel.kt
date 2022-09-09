package com.example.mercadolibre.presentation.features.product_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.domain.list.ListUseCases
import com.example.mercadolibre.data.entities.dto.ProductDto
import com.example.mercadolibre.data.entities.exceptions.CurrencyNotFoundException
import com.example.mercadolibre.data.entities.exceptions.ProductByNameNotFoundException
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
import com.example.mercadolibre.data.helpers.ProductHelper
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.helpers.toProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listUseCases: ListUseCases,
    private val errorLogger: IErrorLogger,
    private val productHelper: ProductHelper
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var products = MutableLiveData<ArrayList<ProductDto>>(arrayListOf())

    private suspend fun getCurrencySymbolById(currencyId: String): Resource<String> {
        return try {
            Resource.Success(listUseCases.getCurrencySymbolById(currencyId))
        } catch (e: CurrencyNotFoundException) {
            Resource.Error(e.message!!)
        }
    }

    fun searchProduct(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                when(val response = listUseCases.getProductByName(name)) {
                    is Resource.Success -> {
                        val productList = response.data?.results
                        val productsDto = ArrayList<ProductDto>()
                        productList?.forEach { product ->
                            val productDto = product.toProductDto()
                            productDto.apply {
                                symbol = getCurrencySymbolById(product.currencyId).data
                                verifiedSeller = productHelper.getSellerNameFromAttributes(product.attributes)
                                thumbnail = productHelper.parseThumbnailUrl(product.thumbnail)
                            }
                            productsDto.add(productDto)
                        }
                        products.postValue(productsDto)
                        isLoading.postValue(false)
                    }
                    is Resource.Error -> {
                        manageError(response.message)
                    }
                }
            } catch (e: ProductByNameNotFoundException) {
                manageError(e.message)
            }
        }
    }

    private fun manageError(errorMessage: String?) {
        isLoading.postValue(false)
        error.postValue(errorMessage)
        errorLogger.logError(errorMessage)
    }

}