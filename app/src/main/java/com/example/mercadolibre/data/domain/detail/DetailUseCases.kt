package com.example.mercadolibre.data.domain.detail

import com.example.mercadolibre.data.domain.currency.GetCurrencySymbolById

data class DetailUseCases(
    val getProductDetailById: GetProductDetailById,
    val getCurrencySymbolById: GetCurrencySymbolById
)