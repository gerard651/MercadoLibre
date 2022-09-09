package com.example.mercadolibre.data.domain.home

import com.example.mercadolibre.data.entities.api.CurrencyResponse
import com.example.mercadolibre.data.entities.exceptions.EmptyCurrenciesException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.HomeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetCurrenciesTest {
    private lateinit var getCurrencies: GetCurrencies

    @RelaxedMockK
    lateinit var homeRepository: HomeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCurrencies = GetCurrencies(homeRepository)
    }

    @Test(expected = EmptyCurrenciesException::class)
    fun `when get an empty list`() = runBlocking {
        val emptyList = listOf<CurrencyResponse>()
        // Given
        coEvery { homeRepository.getCurrencies() } returns Resource.Success(emptyList)

        // When
        getCurrencies()

        // Then
        fail()
    }

    @Test
    fun `when get a not empty list`() = runBlocking {
        val notEmptyList = listOf(CurrencyResponse("UYU", "$"))
        // Given
        coEvery { homeRepository.getCurrencies() } returns Resource.Success(notEmptyList)

        // When
        getCurrencies()

        // Then
        coVerify(exactly = 1) { homeRepository.getCurrencies() }
    }

}