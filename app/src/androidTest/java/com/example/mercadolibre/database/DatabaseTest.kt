package com.example.mercadolibre.database

import com.example.mercadolibre.data.database.dao.CurrenciesDao
import com.example.mercadolibre.data.database.dao.SearchHistoryDao
import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto
import com.example.mercadolibre.data.helpers.toCurrency
import com.example.mercadolibre.data.helpers.toSearchEntityTest
import com.example.mercadolibre.di.DatabaseModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class DatabaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var currenciesDao: CurrenciesDao

    @Inject
    lateinit var searchHistoryDao: SearchHistoryDao

    @Inject
    lateinit var currency: CurrencyResponse

    @Inject
    lateinit var searchHistoryDto: SearchHistoryDto

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun insertCurrency() = runBlocking {
        val currencyEntity = currency.toCurrency()
        currenciesDao.insertCurrencies(listOf(currencyEntity))
        val allCurrencies = currenciesDao.getCurrencies()
        assertTrue(allCurrencies.contains(currencyEntity))
    }

    @Test
    fun getCurrencies() = runBlocking {
        val currencyEntity = currency.toCurrency()
        currenciesDao.insertCurrencies(listOf(currencyEntity))
        assertTrue(currenciesDao.getCurrencies().isNotEmpty())
    }

    @Test
    fun getCurrencyBySymbol() = runBlocking {
        val currencyEntity = currency.toCurrency()
        currenciesDao.insertCurrencies(listOf(currencyEntity))
        val symbol = currenciesDao.getCurrencySymbolById(currencyEntity.id)
        assertTrue(symbol.isNotEmpty())
    }

    @Test
    fun insertSearch() = runBlocking {
        val searchEntity = searchHistoryDto.toSearchEntityTest()
        searchHistoryDao.insertSearch(searchEntity)
        val allCurrencies = searchHistoryDao.getSearchHistory()
        assertTrue(allCurrencies.contains(searchEntity))
    }

    @Test
    fun getSearches() = runBlocking {
        val searchEntity = searchHistoryDto.toSearchEntityTest()
        searchHistoryDao.insertSearch(searchEntity)
        val allCurrencies = searchHistoryDao.getSearchHistory()
        assertTrue(allCurrencies.contains(searchEntity))
    }

    @Test
    fun existSearchInHistory() = runBlocking {
        val searchEntity = searchHistoryDto.toSearchEntityTest()
        searchHistoryDao.insertSearch(searchEntity)
        assertTrue(searchHistoryDao.existSearchInHistory(searchEntity.text))
    }

    @Test
    fun updateSearchHistoryByText() = runBlocking {
        val searchEntity = searchHistoryDto.toSearchEntityTest()
        val newTimestamp = System.currentTimeMillis()
        searchHistoryDao.insertSearch(searchEntity)
        searchHistoryDao.updateSearchHistoryByText(searchEntity.text, newTimestamp)
        val searchHistory = searchHistoryDao.getSearchHistory().firstOrNull { it.timestamp == newTimestamp }
        assertTrue(searchHistory != null)
    }



}