package com.example.mercadolibre.data.domain.search

import com.example.mercadolibre.data.entities.exceptions.InvalidSearchException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import javax.inject.Inject

class IsValidSearch @Inject constructor(
    private val repository: SearchRepository
) {

    @Throws(InvalidSearchException::class)
    suspend operator fun invoke(searchText: String): Resource<Boolean> {
        val isValidResource = repository.isValidSearch(searchText)
        if(isValidResource.data != true)
            throw InvalidSearchException()
        return isValidResource
    }

}