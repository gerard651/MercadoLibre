package com.example.mercadolibre.presentation.adapters

import com.bumptech.glide.Glide
import com.example.mercadolibre.R
import com.example.mercadolibre.core.base.BaseViewHolderAdapter
import com.example.mercadolibre.data.entities.dto.ProductDto
import com.example.mercadolibre.databinding.ItemProductBinding

class ProductListAdapter(
    var onProductClicked: (String) -> Unit
) : BaseViewHolderAdapter<ProductDto, ItemProductBinding>() {

    override fun layoutId() = R.layout.item_product

    override fun onBind(item: ProductDto) {
        with(binding) {
            Glide.with(root.context)
                .load(item.thumbnail)
                .into(productImage)
            container.setOnClickListener {
                onProductClicked(item.id)
            }
            productName.text = item.title
            if(item.officialStoreId != null)
                seller.text = root.context.getString(R.string.seller, item.verifiedSeller)
            price.text = root.context.getString(R.string.price, item.symbol, item.price.toInt())
        }
    }

}