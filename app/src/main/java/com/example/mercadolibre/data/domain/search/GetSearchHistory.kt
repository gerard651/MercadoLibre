package com.example.mercadolibre.data.domain.search

import com.example.mercadolibre.data.entities.database.SearchHistoryEntity
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import javax.inject.Inject

class GetSearchHistory @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(): Resource<List<SearchHistoryEntity>> {
        return repository.getSearchHistory()
    }

}