package com.example.mercadolibre.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mercadolibre.R
import com.example.mercadolibre.core.base.BaseViewPagerAdapter
import com.example.mercadolibre.data.entities.api.ProductPictureResponse
import com.example.mercadolibre.databinding.ItemImageBinding

class ImagesSliderAdapter(
    private val context: Context,
    private val pictureList: ArrayList<ProductPictureResponse>
) : BaseViewPagerAdapter<ProductPictureResponse, ItemImageBinding>(pictureList) {

    override fun setupView(container: ViewGroup, position: Int): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.item_image, container, false)
        val image = itemView.findViewById(R.id.image) as ImageView

        Glide.with(itemView.context)
            .load(pictureList[position].image)
            .into(image)
        container.addView(itemView)
        return itemView
    }

}