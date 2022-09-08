package com.example.mercadolibre.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.entities.dto.ProductDetailDto
import com.example.mercadolibre.data.helpers.ProductHelper
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.helpers.toProductDetailDto
import com.example.mercadolibre.data.interfaces.IErrorLogger
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val errorLogger: IErrorLogger,
    private val productHelper: ProductHelper
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var productDetail = MutableLiveData<ProductDetailDto>()

    private suspend fun getCurrencySymbolById(currencyId: String): String {
        return repository.getCurrencyById(currencyId)
    }

    fun getProductDetailById(id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when(val response = repository.getProductDetailById(id)) {
                is Resource.Success -> {
                    val productDetailResponse = response.data!!
                    val productDetailDto = productDetailResponse.toProductDetailDto()
                    productDetailDto.symbol = getCurrencySymbolById(productDetailResponse.currencyId)
                    productDetailDto.verifiedSeller = productHelper.getSellerNameFromAttributes(productDetailResponse.attributes)
                    productDetail.postValue(productDetailDto)
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