package com.example.mercadolibre.presentation.features.product_search

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibre.R
import com.example.mercadolibre.core.Constants.ARGUMENT_PRODUCT_NAME
import com.example.mercadolibre.core.base.BaseFragment
import com.example.mercadolibre.data.entities.database.SearchHistoryDb
import com.example.mercadolibre.data.viewmodel.SearchViewModel
import com.example.mercadolibre.databinding.FragmentSearchBinding
import com.example.mercadolibre.presentation.adapters.SearchHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    override fun getLayoutId() = R.layout.fragment_search

    override fun setupView() {
        requestInputFocus()
        setupSearchAdapter()
        setupSearchViewWhite()
        setupObservers()
        getSearchHistory()
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                showOrHideLoading(isLoading)
            }
            error.observe(viewLifecycleOwner) { errorMessage ->
                showError(errorMessage)
            }
            searchHistory.observe(viewLifecycleOwner) { searchList ->
                searchHistoryAdapter.submitList(searchList)
            }
        }
    }

    private fun requestInputFocus() {
        binding.searchView.requestInputFocus()
    }

    private fun setupSearchViewWhite() {
        with(binding) {
            searchView.setupView(
                onSearch = { productName ->
                    viewModel.insertOrUpdateSearch(
                        SearchHistoryDb(
                            text = productName,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                    goToListScreen(productName)
            },  onBackPressed = {
                goToHomeScreen()
            })
        }
    }

    private fun setupSearchAdapter() {
        with(binding) {
            searchHistoryAdapter = SearchHistoryAdapter(
                onArrowClicked = { productName ->
                    searchView.setText(productName)
                    requestInputFocus()
                },
                onHistoryClicked = { productName ->
                    viewModel.updateSearch(productName)
                    goToListScreen(productName)
                    showOrHideKeyboard(false)
                })
            recyclerviewSearchHistorial.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = searchHistoryAdapter
            }
        }
    }

    private fun getSearchHistory() {
        viewModel.getSearchHistory()
    }

    private fun goToHomeScreen() {
        findNavController().navigateUp()
    }

    private fun goToListScreen(productName: String) {
        val bundle = Bundle()
        bundle.putString(ARGUMENT_PRODUCT_NAME, productName)
        findNavController().navigate(R.id.action_fragmentSearch_to_fragmentList, bundle)
    }


}