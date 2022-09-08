package com.example.mercadolibre.presentation

import com.example.mercadolibre.R
import com.example.mercadolibre.core.base.BaseActivity
import com.example.mercadolibre.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView() {
        changeStatusBarIconColors()
    }

}