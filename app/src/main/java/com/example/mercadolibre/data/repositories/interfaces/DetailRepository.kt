package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import com.example.mercadolibre.data.entities.api.ProductDetailResponse

interface DetailRepository {

    suspend fun getCurrencyById(id: String): String

    suspend fun getProductDetailById(id: String): Resource<ProductDetailResponse>

}