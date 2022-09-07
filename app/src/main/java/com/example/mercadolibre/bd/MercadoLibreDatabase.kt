package com.example.mercadolibre.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mercadolibre.feature.product_search.SearchHistory

@Database(
    entities = [SearchHistory::class],
    version = 1,
    exportSchema = false
)
abstract class MercadoLibreDatabase: RoomDatabase() {
    abstract val searchHistoryDao: SearchHistoryDao
}