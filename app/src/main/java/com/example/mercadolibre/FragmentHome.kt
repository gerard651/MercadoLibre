package com.example.mercadolibre

import androidx.navigation.fragment.findNavController
import com.example.mercadolibre.base.BaseFragment
import com.example.mercadolibre.databinding.FragmentHomeBinding

class FragmentHome : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setupView() {
        hideKeyboard()
        setupSearchView()
    }

    private fun setupSearchView() {
        with(binding) {
            searchViewYellow.setupView { hasFocus ->
                if(hasFocus) {
                    goToSearchScreen()
                }
            }
        }
    }

    private fun goToSearchScreen() {
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentSearch)
    }

}