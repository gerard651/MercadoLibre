package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import com.example.mercadolibre.data.entities.api.SearchResponse

interface ListRepository {

    suspend fun getCurrencyById(id: String): String

    suspend fun getProductsByName(productName: String): Resource<SearchResponse>

}