package com.example.mercadolibre.presentation.features.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mercadolibre.R
import com.example.mercadolibre.core.base.BaseFragment
import com.example.mercadolibre.data.viewmodel.HomeViewModel
import com.example.mercadolibre.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_home

    override fun setupView() {
        showOrHideKeyboard(false)
        setupObservers()
        getCurrencies()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setupView { hasFocus ->
            if(hasFocus) {
                goToSearchScreen()
            }
        }
    }

    private fun setupObservers() {
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun getCurrencies() {
        viewModel.getCurrencies()
    }

    private fun goToSearchScreen() {
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentSearch)
    }

    private fun removeObservers() {
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

}