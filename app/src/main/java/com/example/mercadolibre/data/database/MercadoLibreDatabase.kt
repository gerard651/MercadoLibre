package com.example.mercadolibre.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.entities.database.CurrencyDb
import com.example.mercadolibre.data.entities.database.SearchHistoryDb

@Database(
    entities = [SearchHistoryDb::class, CurrencyDb::class],
    version = Constants.DB_VERSION,
    exportSchema = false
)
abstract class MercadoLibreDatabase: RoomDatabase() {
    abstract val searchHistoryDao: SearchHistoryDao
    abstract val currenciesDao: CurrenciesDao
}