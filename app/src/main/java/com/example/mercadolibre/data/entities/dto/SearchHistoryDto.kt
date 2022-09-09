package com.example.mercadolibre.data.entities.dto

import javax.inject.Inject

data class SearchHistoryDto @Inject constructor(
    val text: String
)