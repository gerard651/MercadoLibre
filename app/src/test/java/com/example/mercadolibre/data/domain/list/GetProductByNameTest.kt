package com.example.mercadolibre.data.domain.list

import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.SearchResponse
import com.example.mercadolibre.data.entities.exceptions.ProductByNameNotFoundException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.ListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetProductByNameTest {
    private lateinit var getProductByName: GetProductByName

    @RelaxedMockK
    lateinit var listRepository: ListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getProductByName = GetProductByName(listRepository)
    }

    @Test
    fun `when product exist`() = runBlocking {
        val notEmptyListResponse = SearchResponse(results = listOf(ProductResponse(id = "1")))
        val productName = "Test"
        // Given
        coEvery { listRepository.getProductsByName(productName) } returns Resource.Success(notEmptyListResponse)

        // When
        getProductByName(productName)

        // Then
        coVerify(exactly = 1) { listRepository.getProductsByName(productName) }
    }

    @Test(expected = ProductByNameNotFoundException::class)
    fun `when product does not exist`() = runBlocking {
        val emptyListResponse = SearchResponse()
        val productName = "Test"
        // Given
        coEvery { listRepository.getProductsByName(productName) } returns Resource.Success(emptyListResponse)

        // When
        getProductByName(productName)

        // Then
        fail()
    }



}