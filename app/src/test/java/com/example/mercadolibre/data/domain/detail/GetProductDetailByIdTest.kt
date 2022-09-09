package com.example.mercadolibre.data.domain.detail

import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.exceptions.ProductDetailNotFoundException
import com.example.mercadolibre.data.helpers.Resource
import com.example.mercadolibre.data.repositories.interfaces.DetailRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetProductDetailByIdTest {
    private lateinit var getProductDetailById: GetProductDetailById

    @RelaxedMockK
    lateinit var detailRepository: DetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getProductDetailById = GetProductDetailById(detailRepository)
    }

    @Test
    fun `when product exist`() = runBlocking {
        val id = "1"
        val existProductDetail = ProductDetailResponse(id = id)
        // Given
        coEvery { detailRepository.getProductDetailById(id) } returns Resource.Success(existProductDetail)

        // When
        getProductDetailById(id)

        // Then
        coVerify(exactly = 1) { detailRepository.getProductDetailById(id) }
    }

    @Test(expected = ProductDetailNotFoundException::class)
    fun `when product does not exist`() = runBlocking {
        val id = "1"
        val notExistProductDetail = ProductDetailResponse()
        // Given
        coEvery { detailRepository.getProductDetailById("1") } returns Resource.Success(notExistProductDetail)

        // When
        getProductDetailById(id)

        // Then
        fail()
    }

}