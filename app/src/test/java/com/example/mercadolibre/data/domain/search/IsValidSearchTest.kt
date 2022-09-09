package com.example.mercadolibre.data.domain.search

import com.example.mercadolibre.data.entities.exceptions.InvalidSearchException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class IsValidSearchTest {
    private lateinit var isValidSearch: IsValidSearch

    @RelaxedMockK
    lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        isValidSearch = IsValidSearch(searchRepository)
    }

    @Test
    fun `when text is valid`() = runBlocking {
        val searchText = "Test"
        // Given
        coEvery { searchRepository.isValidSearch(searchText) } returns Resource.Success(true)

        // When
        isValidSearch(searchText)

        // Then
        coVerify(exactly = 1) { searchRepository.isValidSearch(searchText) }
    }

    @Test(expected = InvalidSearchException::class)
    fun `when text is invalid`() = runBlocking {
        val searchText = "Test"
        // Given
        coEvery { searchRepository.isValidSearch(searchText) } returns Resource.Success(false)

        // When
        isValidSearch(searchText)

        // Then
        fail()
    }



}