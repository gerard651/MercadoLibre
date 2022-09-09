package com.example.mercadolibre.di

import com.example.mercadolibre.data.api.SearchApi
import com.example.mercadolibre.data.domain.currency.GetCurrencySymbolById
import com.example.mercadolibre.data.domain.list.GetProductByName
import com.example.mercadolibre.data.domain.list.ListUseCases
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
        searchApi: SearchApi
    ): ListRepository {
        return ListRepositoryImpl(searchApi)
    }

    @Provides
    @Singleton
    fun provideListUseCases(
        getCurrencySymbolById: GetCurrencySymbolById,
        getProductByName: GetProductByName
    ): ListUseCases {
        return ListUseCases(getCurrencySymbolById, getProductByName)
    }

}