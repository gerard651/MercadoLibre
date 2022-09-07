package com.example.mercadolibre.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mercadolibre.R
import com.example.mercadolibre.databinding.SearchviewYellowComponentBinding

class CustomSearchYellowView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var binding: SearchviewYellowComponentBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.searchview_yellow_component, this)
        binding = SearchviewYellowComponentBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setText(text: String) {
        binding.input.apply {
            setText(text)
        }
    }

    fun setupView(
        onFocusChange: (Boolean) -> Unit = {}
    ) {
        binding.container.setOnClickListener {
            onFocusChange(true)
        }
    }



}