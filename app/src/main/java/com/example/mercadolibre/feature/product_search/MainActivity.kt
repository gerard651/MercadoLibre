package com.example.mercadolibre.feature.product_search

import com.example.mercadolibre.R
import com.example.mercadolibre.base.BaseActivity
import com.example.mercadolibre.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView() {

    }


}