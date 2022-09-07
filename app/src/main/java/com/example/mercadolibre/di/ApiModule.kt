package com.example.mercadolibre.di

import android.content.Context
import androidx.room.Room
import com.example.mercadolibre.BuildConfig
import com.example.mercadolibre.bd.MercadoLibreDatabase
import com.example.mercadolibre.bd.SearchHistoryDao
import com.example.mercadolibre.feature.product_search.SearchApi
import com.example.mercadolibre.feature.product_search.SearchRepository
import com.example.mercadolibre.feature.product_search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api: SearchApi, searchHistoryDao: SearchHistoryDao): SearchRepository {
        return SearchRepositoryImpl(api, searchHistoryDao)
    }

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
    fun provideSearchHistoryDao(database: MercadoLibreDatabase): SearchHistoryDao {
        return database.searchHistoryDao
    }

}