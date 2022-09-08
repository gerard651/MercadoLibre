package com.example.mercadolibre.presentation.adapters

import com.example.mercadolibre.R
import com.example.mercadolibre.core.base.BaseViewHolderAdapter
import com.example.mercadolibre.data.entities.dto.SearchHistoryDto
import com.example.mercadolibre.databinding.ItemHistorialBinding

class SearchHistoryAdapter(
    private val onArrowClicked: (String) -> Unit,
    private val onHistoryClicked: (String) -> Unit
) : BaseViewHolderAdapter<SearchHistoryDto, ItemHistorialBinding>() {

    override fun layoutId() = R.layout.item_historial

    override fun onBind(item: SearchHistoryDto) {
        with(binding) {
            historialText.text = item.text
            imageArrow.setOnClickListener {
                onArrowClicked(item.text)
            }
            container.setOnClickListener {
                onHistoryClicked(item.text)
            }
        }
    }

}