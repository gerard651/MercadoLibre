package com.example.mercadolibre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolibre.data.database.MercadoLibreDatabase
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideDatabaseTest(@ApplicationContext appContext: Context): MercadoLibreDatabase {
        return Room.inMemoryDatabaseBuilder(
            appContext,
            MercadoLibreDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideSearchHistoryDaoTest(database: MercadoLibreDatabase): SearchHistoryDao {
        return database.searchHistoryDao
    }

    @Provides
    @Singleton
    fun provideCurrenciesDaoTest(database: MercadoLibreDatabase): CurrenciesDao {
        return database.currenciesDao
    }

    @Provides
    @Singleton
    fun provideString(): String = "Test"

    @Provides
    @Singleton
    @Named("currencyUy")
    fun provideCurrencyUy() = CurrencyResponse("UYU", "$")

    @Provides
    @Singleton
    @Named("currencyRandom")
    fun provideCurrencyUsd() = CurrencyResponse("Test", "$")

    @Provides
    @Singleton
    fun provideSearchDto() = SearchHistoryDto("Search")

}