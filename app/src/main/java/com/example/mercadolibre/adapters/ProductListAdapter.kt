package com.example.mercadolibre.adapters

import com.bumptech.glide.Glide
import com.example.mercadolibre.R
import com.example.mercadolibre.base.BaseViewHolderAdapter
import com.example.mercadolibre.databinding.ItemProductBinding
import com.example.mercadolibre.model.Product
import com.example.mercadolibre.utils.parseThumbailUrl


class ProductListAdapter(
    var onProductClicked: (Product) -> Unit
) : BaseViewHolderAdapter<Product, ItemProductBinding>() {

    override fun layoutId(): Int {
        return R.layout.item_product
    }

    override fun onBind(item: Product) {
        with(binding) {
            Glide.with(root.context)
                .load(item.thumbnail.parseThumbailUrl())
                .into(productImage)
            container.setOnClickListener {
                onProductClicked(item)
            }
            productName.text = item.title
        }
    }

}