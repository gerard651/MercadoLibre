package com.example.mercadolibre.data.domain.list

import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.entities.exceptions.ProductByNameNotFoundException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
import javax.inject.Inject

class GetProductByName @Inject constructor(
    private val repository: ListRepository
) {

    @Throws(ProductByNameNotFoundException::class)
    suspend operator fun invoke(name: String): Resource<SearchResponse> {
        val products = repository.getProductsByName(name)
        if(products.data == null || products.data.results.isEmpty())
            throw ProductByNameNotFoundException()
        return products
    }

}