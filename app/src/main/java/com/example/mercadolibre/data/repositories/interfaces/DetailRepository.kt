package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.helpers.Resource

interface DetailRepository {

    suspend fun getProductDetailById(id: String): Resource<ProductDetailResponse>

}