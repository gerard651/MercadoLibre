package com.example.mercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var id: String,
    var title: String,
    var thumbnail: String
): Parcelable
