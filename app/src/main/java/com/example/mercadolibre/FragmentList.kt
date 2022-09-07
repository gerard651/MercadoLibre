package com.example.mercadolibre

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibre.adapters.ProductListAdapter
import com.example.mercadolibre.base.BaseFragment
import com.example.mercadolibre.databinding.FragmentListBinding
import com.example.mercadolibre.feature.product_search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentList : BaseFragment<FragmentListBinding>() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var productListAdapter: ProductListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun setupView() {
        setupProductListAdapter()
        setupObservers()
        val productName = getProductName()
        searchProduct(productName)
        setupSearchView(productName)
    }

    private fun getProductName(): String {
        return arguments?.getString("productName", "") ?: ""
    }

    private fun setupProductListAdapter() {
        productListAdapter = ProductListAdapter { productName ->

        }
        binding.recyclerviewProductList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = productListAdapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                if(isLoading)
                    showLoading()
                else
                    hideLoading()
            }
            error.observe(viewLifecycleOwner) {

            }
            products.observe(viewLifecycleOwner) { products ->
                if(products.isNotEmpty()) {
                    productListAdapter.submitList(ArrayList(products))
                }
            }
        }
    }

    private fun setupSearchView(productName: String) {
        with(binding.searchViewYellow) {
            setText(productName)
            setupView { hasFocus ->
                if(hasFocus) {
                    goToHomeScreen()
                }
            }
        }
    }

    private fun searchProduct(productName: String) {
        viewModel.searchProduct(productName)
        hideKeyboard()
    }

    private fun goToHomeScreen() {
        findNavController().navigateUp()
    }

}