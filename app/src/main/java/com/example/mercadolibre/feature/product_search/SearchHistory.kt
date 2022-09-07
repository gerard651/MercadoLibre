package com.example.mercadolibre.feature.product_search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    var timestamp: Long
)