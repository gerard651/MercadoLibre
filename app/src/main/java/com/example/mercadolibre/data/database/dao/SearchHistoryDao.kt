package com.example.mercadolibre.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mercadolibre.data.entities.database.SearchHistoryEntity

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(searchHistory: SearchHistoryEntity)

    @Query("SELECT * FROM SearchHistoryEntity ORDER BY timestamp DESC LIMIT 5")
    suspend fun getSearchHistory(): List<SearchHistoryEntity>

    @Query("SELECT EXISTS(SELECT * FROM SearchHistoryEntity WHERE text = :productName)")
    suspend fun existSearchInHistory(productName: String) : Boolean

    @Query("UPDATE SearchHistoryEntity SET timestamp = :timestamp WHERE text = :searchText")
    suspend fun updateSearchHistoryByText(searchText: String, timestamp: Long)

}