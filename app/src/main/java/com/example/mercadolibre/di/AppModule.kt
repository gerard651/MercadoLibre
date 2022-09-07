package com.example.mercadolibre.di

import com.example.mercadolibre.BuildConfig.API_BASE_URL
import com.example.mercadolibre.feature.product_search.SearchApi
import com.example.mercadolibre.feature.product_search.SearchRepository
import com.example.mercadolibre.feature.product_search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
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
    fun provideSearchRepository(api: SearchApi): SearchRepository {
        return SearchRepositoryImpl(api)
    }

}