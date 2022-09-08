package com.example.mercadolibre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolibre.data.database.MercadoLibreDatabase
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MercadoLibreDatabase {
        return Room.databaseBuilder(
            appContext,
            MercadoLibreDatabase::class.java,
            "DB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSearchHistoryDao(database: MercadoLibreDatabase): SearchHistoryDao {
        return database.searchHistoryDao
    }

    @Provides
    @Singleton
    fun provideCurrenciesDao(database: MercadoLibreDatabase): CurrenciesDao {
        return database.currenciesDao
    }

}