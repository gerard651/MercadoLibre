package com.example.mercadolibre.di

import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.repositories.implementation.CurrencyRepositoryImpl
import com.example.mercadolibre.data.repositories.interfaces.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        currenciesDao: CurrenciesDao
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currenciesDao)
    }

}