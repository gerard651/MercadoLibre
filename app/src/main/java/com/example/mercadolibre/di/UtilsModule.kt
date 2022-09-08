package com.example.mercadolibre.di

import com.example.mercadolibre.data.helpers.ErrorLogger
import com.example.mercadolibre.data.helpers.ProductHelper
import com.example.mercadolibre.data.interfaces.IErrorLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideErrorLogger(): IErrorLogger {
        return ErrorLogger()
    }

    @Provides
    @Singleton
    fun provideProductHelper(): ProductHelper {
        return ProductHelper()
    }

}