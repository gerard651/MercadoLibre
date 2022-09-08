package com.example.mercadolibre.di

import com.example.mercadolibre.BuildConfig
import com.example.mercadolibre.data.api.CurrenciesApi
import com.example.mercadolibre.data.api.ProductApi
import com.example.mercadolibre.data.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideCurrenciesApi(retrofit: Retrofit): CurrenciesApi {
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemsApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

}