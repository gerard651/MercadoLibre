package com.example.mercadolibre.data.repositories.interfaces

import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.helpers.Resource

interface ListRepository {

    suspend fun getProductsByName(productName: String): Resource<SearchResponse>

}