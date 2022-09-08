package com.example.mercadolibre.di

import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.repositories.implementation.SearchRepositoryImpl
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchHistoryDao: SearchHistoryDao
    ): SearchRepository {
        return SearchRepositoryImpl(searchHistoryDao)
    }

}