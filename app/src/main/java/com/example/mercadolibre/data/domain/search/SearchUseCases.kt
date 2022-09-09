package com.example.mercadolibre.data.domain.search

data class SearchUseCases(
    val insertOrUpdateSearch: InsertOrUpdateSearch,
    val updateSearch: UpdateSearch,
    val getSearchHistory: GetSearchHistory,
    val isValidSearch: IsValidSearch
)