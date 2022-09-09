package com.example.mercadolibre.data.domain.search

import com.example.mercadolibre.data.entities.database.SearchHistoryEntity
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import javax.inject.Inject

class InsertOrUpdateSearch @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(searchHistoryEntity: SearchHistoryEntity) {
        repository.insertOrUpdateSearchHistory(searchHistoryEntity)
    }

}