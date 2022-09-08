package com.example.mercadolibre.presentation.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mercadolibre.R
import com.example.mercadolibre.databinding.SearchViewYellowComponentBinding

class CustomSearchViewYellow(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var binding: SearchViewYellowComponentBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.search_view_yellow_component, this)
        binding = SearchViewYellowComponentBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setText(text: String) {
        binding.input.text = text
    }

    fun setupView(onFocusChange: (Boolean) -> Unit) {
        binding.container.setOnClickListener {
            onFocusChange(true)
        }
    }

}