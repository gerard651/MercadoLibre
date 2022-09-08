package com.example.mercadolibre.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mercadolibre.data.helpers.LoadingHelper
import com.example.mercadolibre.data.helpers.hideKeyboard
import com.example.mercadolibre.data.helpers.showKeyboard
import com.example.mercadolibre.data.interfaces.IErrorUser
import es.dmoral.toasty.Toasty

abstract class BaseFragment<B: ViewDataBinding> : Fragment(), IErrorUser {
    lateinit var binding: B
    private var loading: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupView()

    fun showOrHideKeyboard(show: Boolean) {
        with(requireActivity()) {
            if(show)
                showKeyboard()
            else
                hideKeyboard()
        }
    }

    fun showOrHideLoading(isLoading: Boolean) {
        LoadingHelper.showOrHideLoading(requireContext(), isLoading)
    }

    override fun showError(errorMessage: String) {
        if(errorMessage.isNotEmpty()) {
            Toasty.error(requireContext(), errorMessage).show()
        }
    }

    /**
     * Cambia el color de los iconos de la status bar a negro
     * para poder contrastar con el color amarillo de la app
     */
    fun changeStatusBarIconColors() {
        val decorView = requireActivity().window.decorView
        if(decorView.systemUiVisibility != View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            decorView.systemUiVisibility = 0
    }


}