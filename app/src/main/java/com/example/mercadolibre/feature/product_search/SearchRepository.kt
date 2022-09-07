package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.model.SearchResponse
import com.example.mercadolibre.utils.Resource

interface SearchRepository {

    suspend fun getProductsByName(productName: String): Resource<SearchResponse>

}