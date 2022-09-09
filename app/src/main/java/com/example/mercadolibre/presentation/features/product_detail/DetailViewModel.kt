package com.example.mercadolibre.presentation.features.product_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.domain.detail.DetailUseCases
import com.example.mercadolibre.data.entities.dto.ProductDetailDto
import com.example.mercadolibre.data.entities.exceptions.ProductDetailNotFoundException
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
import com.example.mercadolibre.data.helpers.ProductHelper
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.helpers.toProductDetailDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    private val errorLogger: IErrorLogger,
    private val productHelper: ProductHelper
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var error = MutableLiveData("")
    var productDetail = MutableLiveData<ProductDetailDto>()

    fun getProductDetailById(id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                when(val response = detailUseCases.getProductDetailById(id)) {
                    is Resource.Success -> {
                        val productDetailResponse = response.data!!
                        val productDetailDto = productDetailResponse.toProductDetailDto()
                        productDetailDto.symbol = detailUseCases.getCurrencySymbolById(productDetailResponse.currencyId)
                        productDetailDto.verifiedSeller = productHelper.getSellerNameFromAttributes(productDetailResponse.attributes)
                        productDetail.postValue(productDetailDto)
                        isLoading.postValue(false)
                    }
                    is Resource.Error -> {
                        manageError(response.message)
                    }
                }
            } catch (e: ProductDetailNotFoundException) {
                manageError(e.message)
            }
        }
    }

    private fun manageError(errorMessage: String?) {
        errorLogger.logError(errorMessage)
        isLoading.postValue(false)
        error.postValue(errorMessage)
    }

}