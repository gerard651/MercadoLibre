package com.example.mercadolibre.data.entities.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("price") val price: Double = 0.0,
    @SerializedName("currency_id") val currencyId: String = "",
    @SerializedName("official_store_id") val officialStoreId: Int? = 0,
    @SerializedName("attributes") val attributes: List<ProductAttributeResponse>? = listOf(),
    @SerializedName("available_quantity") val availableQuantity: Int = 0,
    @SerializedName("sold_quantity") val soldQuantity: Int = 0,
    @SerializedName("condition") val condition: String = "",
    @SerializedName("pictures") val pictures: ArrayList<ProductPictureResponse> = arrayListOf()
): Parcelable
