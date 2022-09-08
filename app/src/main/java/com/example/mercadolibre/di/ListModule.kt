package com.example.mercadolibre.di

import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.api.SearchApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.repositories.implementation.ListRepositoryImpl
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    @Singleton
    fun provideListRepository(
        searchApi: SearchApi,
        currenciesDao: CurrenciesDao
    ): ListRepository {
        return ListRepositoryImpl(searchApi, currenciesDao)
    }

}