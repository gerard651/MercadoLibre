package com.example.mercadolibre.data.domain.currency

import com.example.mercadolibre.data.entities.exceptions.CurrencyNotFoundException
import com.example.mercadolibre.data.repositories.interfaces.CurrencyRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetCurrencySymbolByIdTest {
    private lateinit var getCurrencySymbolById: GetCurrencySymbolById

    @RelaxedMockK
    lateinit var currencyRepository: CurrencyRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCurrencySymbolById = GetCurrencySymbolById(currencyRepository)
    }

    @Test
    fun `when currency exist`() = runBlocking {
        val id = "1"
        val symbol = "$"
        // Given
        coEvery { currencyRepository.getCurrencyById(id) } returns symbol

        // When
        getCurrencySymbolById(id)

        // Then
        coVerify(exactly = 1) { currencyRepository.getCurrencyById(id) }
    }

    @Test(expected = CurrencyNotFoundException::class)
    fun `when currency does not exist`() = runBlocking {
        val id = "1"
        // Given
        coEvery { currencyRepository.getCurrencyById(id) } returns ""

        // When
        getCurrencySymbolById(id)

        // Then
        fail()
    }



}