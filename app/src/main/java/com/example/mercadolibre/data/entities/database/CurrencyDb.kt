package com.example.mercadolibre.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyDb(
    @PrimaryKey val id: String,
    val symbol: String
)