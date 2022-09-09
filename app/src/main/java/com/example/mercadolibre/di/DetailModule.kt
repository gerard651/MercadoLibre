package com.example.mercadolibre.di

import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.domain.currency.GetCurrencySymbolById
import com.example.mercadolibre.data.domain.detail.DetailUseCases
import com.example.mercadolibre.data.domain.detail.GetProductDetailById
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
        productApi: ProductApi
    ): DetailRepository {
        return DetailRepositoryImpl(productApi)
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(
        getProductDetailById: GetProductDetailById,
        getCurrencySymbolById: GetCurrencySymbolById
    ): DetailUseCases {
        return DetailUseCases(getProductDetailById, getCurrencySymbolById)
    }

}