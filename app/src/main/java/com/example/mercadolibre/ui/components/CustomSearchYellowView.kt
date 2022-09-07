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
        with(binding) {
            container.setOnClickListener {
                input.requestFocus()
            }
        }
    }

    fun setText(text: String, needFocus: Boolean = true) {
        binding.input.apply {
            setText(text)
            if(needFocus)
                requestFocus()
            setSelection(text.length)
        }
    }

    fun setupView(
        onFocusChange: (Boolean) -> Unit = {}
    ) {
        binding.input.setOnFocusChangeListener { _, hasFocus ->
            onFocusChange(hasFocus || (!hasFocus && binding.input.text.isNotEmpty()))
        }
    }



}