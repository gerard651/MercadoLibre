package com.example.mercadolibre.data.domain.search

import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import javax.inject.Inject

class UpdateSearch @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(searchTex: String, timestamp: Long) {
        repository.updateSearchHistory(searchTex, timestamp)
    }

}