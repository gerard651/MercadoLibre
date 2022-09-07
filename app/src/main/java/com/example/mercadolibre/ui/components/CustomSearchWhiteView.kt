package com.example.mercadolibre.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mercadolibre.R
import com.example.mercadolibre.databinding.SearchviewWhiteComponentBinding


class CustomSearchWhiteView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var binding: SearchviewWhiteComponentBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.searchview_white_component, this)
        binding = SearchviewWhiteComponentBinding.inflate(LayoutInflater.from(context), this, true)
        with(binding) {
            container.setOnClickListener {
                requestInputFocus()
            }
        }
    }

    fun requestInputFocus() {
        binding.input.requestFocus()
    }

    fun setText(text: String) {
        binding.input.apply {
            setText(text)
            requestFocus()
            setSelection(text.length)
        }
    }

    fun setupView(
        onFocusChange: (Boolean) -> Unit = {},
        onSearch: (String) -> Unit = {},
        onBackPressed: () -> Unit = {}
    ) {
        with(binding) {
            with(input) {
                setOnFocusChangeListener { _, hasFocus ->
                    onFocusChange(hasFocus || (!hasFocus && binding.input.text.isNotEmpty()))
                }
                setOnEditorActionListener { _, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event?.action == KeyEvent.ACTION_DOWN &&
                        event.keyCode == KeyEvent.KEYCODE_ENTER) {
                        onSearch(text.toString())
                    }
                    true
                }
            }
            with(back) {
                setOnClickListener {
                    onBackPressed()
                }
            }
        }
    }



}