package com.example.mercadolibre.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mercadolibre.R
import com.example.mercadolibre.utils.createDialogWithoutBackground


abstract class BaseFragment<B: ViewDataBinding> : Fragment() {
    lateinit var binding: B
    var loading: Dialog? = null
    lateinit var test: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        test = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupView()

    fun hideKeyboard() {
        val imm: InputMethodManager = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = requireActivity().currentFocus
        if (view == null) {
            view = View(requireContext())
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun showLoading() {
        loading = requireContext().createDialogWithoutBackground(R.layout.loading_component)
        loading?.show()
    }

    fun hideLoading() {
        loading?.dismiss()
    }

}