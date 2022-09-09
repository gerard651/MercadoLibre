package com.example.mercadolibre.presentation.features.product_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mercadolibre.data.domain.detail.DetailUseCases
import com.example.mercadolibre.data.entities.api.ProductDetailResponse
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
import com.example.mercadolibre.data.helpers.ProductHelper
import com.example.mercadolibre.data.helpers.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var detailUseCases: DetailUseCases

    @RelaxedMockK
    private lateinit var errorLogger: IErrorLogger

    @RelaxedMockK
    private lateinit var productHelper: ProductHelper

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailViewModel = DetailViewModel(detailUseCases, errorLogger, productHelper)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get product detail with id`() = runTest {
        val id = "1"
        val title = "Title"
        val productDetail = ProductDetailResponse(
            id = id,
            title = title
        )

        // Given
        coEvery { detailUseCases.getProductDetailById(id) } returns Resource.Success(productDetail)

        // When
        detailViewModel.getProductDetailById(id)

        // Then
        assertTrue(detailViewModel.productDetail.value!!.title == title)
    }

}