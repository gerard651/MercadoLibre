package com.example.mercadolibre.data.domain.detail

import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.exceptions.ProductDetailNotFoundException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import javax.inject.Inject

class GetProductDetailById @Inject constructor(
    private val repository: DetailRepository
) {

    @Throws(ProductDetailNotFoundException::class)
    suspend operator fun invoke(id: String): Resource<ProductDetailResponse> {
        val productDetail = repository.getProductDetailById(id)
        if(productDetail.data == null || productDetail.data.id.isEmpty())
            throw ProductDetailNotFoundException()
        return productDetail
    }

}