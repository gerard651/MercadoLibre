package com.example.mercadolibre.data.entities.dto

import com.example.mercadolibre.data.entities.api.ProductPictureResponse

data class ProductDetailDto(
    val title: String = "",
    val price: Double = 0.0,
    val officialStoreId: Int? = 0,
    val availableQuantity: Int = 0,
    val soldQuantity: Int = 0,
    val condition: String = "",
    val pictures: ArrayList<ProductPictureResponse> = arrayListOf(),
    var verifiedSeller: String? = "",
    var symbol: String? = "",
)
