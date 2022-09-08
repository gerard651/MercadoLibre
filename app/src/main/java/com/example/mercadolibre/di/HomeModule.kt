package com.example.mercadolibre.di

import com.example.mercadolibre.data.api.CurrenciesApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.repositories.implementation.HomeRepositoryImpl
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        currenciesApi: CurrenciesApi,
        currenciesDao: CurrenciesDao
    ): HomeRepository {
        return HomeRepositoryImpl(currenciesApi, currenciesDao)
    }

}