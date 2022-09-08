package com.example.mercadolibre.data.di

import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.repositories.implementation.DetailRepositoryImpl
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Provides
    @Singleton
    fun provideDetailRepository(
        productApi: ProductApi,
        currenciesDao: CurrenciesDao
    ): DetailRepository {
        return DetailRepositoryImpl(productApi, currenciesDao)
    }

}