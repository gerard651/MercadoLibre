package com.example.mercadolibre.presentation.features.product_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mercadolibre.data.domain.list.ListUseCases
import com.example.mercadolibre.data.entities.api.ProductResponse
import com.example.mercadolibre.data.entities.api.SearchResponse
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
class ListViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var listUseCases: ListUseCases

    @RelaxedMockK
    private lateinit var errorLogger: IErrorLogger

    @RelaxedMockK
    private lateinit var productHelper: ProductHelper

    private lateinit var listViewModel: ListViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        listViewModel = ListViewModel(listUseCases, errorLogger, productHelper)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get product list by name`() = runTest {
        val name = "Test name"
        val product = ProductResponse(
            "1", "Test", "www", 0.0, "Test", 0, listOf(), 0, 0, "Test", arrayListOf()
        )
        val searchResponse = SearchResponse(
            results = arrayListOf(product)
        )
        // Given
        coEvery { listUseCases.getProductByName(name) } returns Resource.Success(searchResponse)

        // When
        listViewModel.searchProduct(name)

        // Then
        assertTrue(listViewModel.products.value?.first()?.id == product.id)
    }

}