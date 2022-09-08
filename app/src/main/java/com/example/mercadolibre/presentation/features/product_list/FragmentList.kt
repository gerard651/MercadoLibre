package com.example.mercadolibre.presentation.features.product_list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mercadolibre.R
import com.example.mercadolibre.core.Constants.ARGUMENT_PRODUCT_ID
import com.example.mercadolibre.core.Constants.ARGUMENT_PRODUCT_NAME
import com.example.mercadolibre.core.base.BaseFragment
import com.example.mercadolibre.data.viewmodel.ListViewModel
import com.example.mercadolibre.databinding.FragmentListBinding
import com.example.mercadolibre.presentation.adapters.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentList : BaseFragment<FragmentListBinding>() {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var productListAdapter: ProductListAdapter

    override fun getLayoutId() = R.layout.fragment_list

    override fun setupView() {
        setupProductListAdapter()
        setupObservers()
        val productName = getProductName()
        searchProduct(productName)
        setupSearchView(productName)
    }

    private fun getProductName(): String {
        return arguments?.getString(ARGUMENT_PRODUCT_NAME, "") ?: ""
    }

    private fun setupProductListAdapter() {
        productListAdapter = ProductListAdapter(
            onProductClicked =  { productId ->
                goToProductDetail(productId)
        })
        binding.recyclerviewProductList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = productListAdapter
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                showOrHideLoading(isLoading)
            }
            error.observe(viewLifecycleOwner) { errorMessage ->
                showError(errorMessage)
            }
            products.observe(viewLifecycleOwner) { products ->
                if(products.isNotEmpty()) {
                    productListAdapter.submitList(products)
                }
            }
        }
    }

    private fun setupSearchView(productName: String) {
        binding.searchView.apply {
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
        showOrHideKeyboard(false)
    }

    private fun goToHomeScreen() {
        findNavController().navigateUp()
    }

    private fun goToProductDetail(id: String) {
        val bundle = Bundle()
        bundle.putString(ARGUMENT_PRODUCT_ID, id)
        findNavController().navigate(R.id.action_fragmentList_to_fragmentDetail, bundle)
    }

}