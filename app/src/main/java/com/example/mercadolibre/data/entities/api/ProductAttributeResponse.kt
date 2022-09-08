package com.example.mercadolibre.data.entities.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductAttributeResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("value_name") val sellerName: String = ""
): Parcelable {

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other?.hashCode() == hashCode()
    }

}
