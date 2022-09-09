package com.example.mercadolibre.data.domain.home

data class HomeUseCases(
    val getCurrenciesUseCase: GetCurrencies,
    val insertCurrencies: InsertCurrencies
)