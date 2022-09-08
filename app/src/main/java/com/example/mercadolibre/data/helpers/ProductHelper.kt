package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.core.Constants.PRODUCT_TAG_TO_GET_VERIFIED_SELLER
import com.example.mercadolibre.core.Constants.PRODUCT_THUMBNAIL_REPLACE_COUNT_OF_DIGITS
import com.example.mercadolibre.core.Constants.PRODUCT_THUMBNAIL_REPLACE_TO_PREFIX
import com.example.mercadolibre.data.entities.api.ProductAttributeResponse
import java.lang.Exception

/**
 * Contiene utilidades para los productos
 */
class ProductHelper {

    /**
     * A partir de una lista de atributos devuelve el nombre/marca del vendedor e
     * en caso de que este verificado
     * @param attributes lista de atributos
     */
    fun getSellerNameFromAttributes(attributes: List<ProductAttributeResponse>?): String {
        if(attributes == null)
            return ""
        return try {
            attributes.first { it.id == PRODUCT_TAG_TO_GET_VERIFIED_SELLER }.sellerName
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Para poder cargar una imagen usando una libreria es necesario remplazar el prefijo
     * http por https
     * @param url path del thumbnail del producto
     */
    fun parseThumbnailUrl(url: String): String {
        return url.replaceRange(0..PRODUCT_THUMBNAIL_REPLACE_COUNT_OF_DIGITS, PRODUCT_THUMBNAIL_REPLACE_TO_PREFIX)
    }

}