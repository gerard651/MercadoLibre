package com.example.mercadolibre.adapters

import com.example.mercadolibre.R
import com.example.mercadolibre.base.BaseViewHolderAdapter
import com.example.mercadolibre.databinding.ItemHistorialBinding
import com.example.mercadolibre.feature.product_search.SearchHistory


class SearchHistoryAdapter(
    private val onArrowClicked: (String) -> Unit,
    private val onHistoryClicked: (String) -> Unit
) : BaseViewHolderAdapter<SearchHistory, ItemHistorialBinding>() {

    override fun layoutId(): Int {
        return R.layout.item_historial
    }

    override fun onBind(item: SearchHistory) {
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