package com.example.mercadolibre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolibre.data.entities.database.SearchHistoryDb

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(searchHistory: SearchHistoryDb)

    @Query("SELECT * FROM SearchHistoryDb ORDER BY timestamp DESC LIMIT 5")
    suspend fun getSearchHistory(): List<SearchHistoryDb>

    @Query("SELECT EXISTS(SELECT * FROM SearchHistoryDb WHERE text = :productName)")
    suspend fun existSearchInHistory(productName: String) : Boolean

    @Query("UPDATE SearchHistoryDb SET timestamp = :timestamp WHERE text = :searchText")
    suspend fun updateSearchHistoryByText(searchText: String, timestamp: Long)

}