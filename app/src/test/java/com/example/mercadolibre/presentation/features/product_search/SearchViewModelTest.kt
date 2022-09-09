package com.example.mercadolibre.presentation.features.product_search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mercadolibre.data.domain.search.SearchUseCases
import com.example.mercadolibre.data.entities.database.SearchHistoryEntity
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
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
class SearchViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var searchUseCases: SearchUseCases

    @RelaxedMockK
    private lateinit var errorLogger: IErrorLogger

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(searchUseCases, errorLogger)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get search history`() = runTest {
        val searchText = "Search text"
        val searchHistory = arrayListOf(SearchHistoryEntity(0, searchText, 1))

        // Given
        coEvery { searchUseCases.getSearchHistory() } returns Resource.Success(searchHistory)

        // When
        searchViewModel.getSearchHistory()

        // Then
        assertTrue(searchViewModel.searchHistory.value!!.first().text == searchText)
    }

}