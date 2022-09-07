package com.example.mercadolibre.base

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<B: ViewDataBinding> : AppCompatActivity(), IDialog {
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

    override fun showError(confirmListener: () -> Unit) {
        confirmListener()
    }

    fun checkPermission(permission: String): Boolean {
        return (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
    }

    fun changeStatusBarIconColors() {
        val decorView = window.decorView
        if(decorView.systemUiVisibility != View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        else
            decorView.systemUiVisibility = 0
    }

    fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}