package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.core.Constants.TAG_TO_GET_VERIFIED_SELLER
import com.example.mercadolibre.core.Constants.THUMBNAIL_REPLACE_COUNT_OF_DIGITS
import com.example.mercadolibre.core.Constants.THUMBNAIL_REPLACE_TO_PREFIX
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import java.lang.Exception

class ProductHelper {

    fun getSellerNameFromAttributes(attributes: List<ProductAttributeResponse>?): String {
        if(attributes == null)
            return ""
        return try {
            attributes.first { it.id == TAG_TO_GET_VERIFIED_SELLER }.sellerName
        } catch (e: Exception) {
            ""
        }
    }

    fun parseThumbnailUrl(url: String): String {
        return url.replaceRange(0..THUMBNAIL_REPLACE_COUNT_OF_DIGITS, THUMBNAIL_REPLACE_TO_PREFIX)
    }

}