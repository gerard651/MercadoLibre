package com.example.mercadolibre.data.repositories.interfaces

interface CurrencyRepository {

    suspend fun getCurrencyById(id: String): String

}