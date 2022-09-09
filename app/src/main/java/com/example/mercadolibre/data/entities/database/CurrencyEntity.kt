package com.example.mercadolibre.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyEntity(
    @PrimaryKey val id: String,
    val symbol: String
)