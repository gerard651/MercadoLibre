package com.example.mercadolibre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolibre.data.entities.database.CurrencyDb

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyDb>)

    @Query("SELECT symbol FROM CurrencyDb WHERE id = :id")
    suspend fun getCurrencySymbolById(id: String): String

}