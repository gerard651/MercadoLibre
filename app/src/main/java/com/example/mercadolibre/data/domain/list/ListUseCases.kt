package com.example.mercadolibre.data.domain.list

import com.example.mercadolibre.data.domain.currency.GetCurrencySymbolById

data class ListUseCases(
    val getCurrencySymbolById: GetCurrencySymbolById,
    val getProductByName: GetProductByName
)