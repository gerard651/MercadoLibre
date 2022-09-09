package com.example.mercadolibre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolibre.data.entities.database.CurrencyEntity

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM CurrencyEntity")
    suspend fun getCurrencies(): List<CurrencyEntity>

    @Query("SELECT symbol FROM CurrencyEntity WHERE id = :id")
    suspend fun getCurrencySymbolById(id: String): String

}