package com.example.mercadolibre.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    var timestamp: Long
)