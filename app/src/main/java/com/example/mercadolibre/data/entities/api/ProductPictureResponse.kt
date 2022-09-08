package com.example.mercadolibre.data.entities.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductPictureResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("secure_url") val image: String = ""
): Parcelable
