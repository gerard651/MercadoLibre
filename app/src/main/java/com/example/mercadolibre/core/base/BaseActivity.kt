package com.example.mercadolibre.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: B
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bindView()
        setupView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupView()

    private fun bindView(){
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    fun changeStatusBarIconColors() {
        val decorView = window.decorView
        if(decorView.systemUiVisibility != View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            decorView.systemUiVisibility = 0
    }

}