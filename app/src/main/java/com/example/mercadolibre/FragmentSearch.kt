package com.example.mercadolibre

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibre.adapters.SearchHistoryAdapter
import com.example.mercadolibre.base.BaseFragment
import com.example.mercadolibre.databinding.FragmentSearchBinding
import com.example.mercadolibre.feature.product_search.SearchHistory
import com.example.mercadolibre.feature.product_search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun setupView() {
        requestInputFocus()
        setupSearchAdapter()
        setupSearchViewWhite()
        setupObservers()
        getSearchHistory()
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                if(it)
                    showLoading()
                else
                    hideLoading()
            }
            error.observe(viewLifecycleOwner) {

            }
            searchHistory.observe(viewLifecycleOwner) {
                searchHistoryAdapter.submitList(ArrayList(it))
            }
        }
    }

    private fun requestInputFocus() {
        binding.searchViewWhite.requestInputFocus()
    }

    private fun setupSearchViewWhite() {
        with(binding) {
            searchViewWhite.setupView(
                onSearch = { productName ->
                    viewModel.insertOrUpdateSearch(
                        SearchHistory(
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
                    searchViewWhite.setText(productName)
                    requestInputFocus()
                },
                onHistoryClicked = { productName ->
                    viewModel.updateSearch(productName)
                    goToListScreen(productName)
                    hideKeyboard()
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
        bundle.putString("productName", productName)
        findNavController().navigate(R.id.action_fragmentSearch_to_fragmentList, bundle)
    }


}