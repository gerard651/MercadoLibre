package com.example.mercadolibre.core

object Constants {

    // Errors
    const val LOG_ERROR_TAG = "ERROR"
    const val LOG_ERROR_APPLICATION_TAG = "ERROR_APPLICATION"

    // Error default messages
    const val ERROR_IO_EXCEPTION = "No se ha podido conectar al servidor."
    const val ERROR_HTTP_EXCEPTION = "Error inesperado."

    // Excepciones
    const val EXCEPTION_INVALID_SEARCH = "La busqueda no cumple el minimo de caracteres"

    // Api
    const val SITE_UY = "MLU"
    const val PRODUCT_TAG_TO_GET_VERIFIED_SELLER = "BRAND"
    const val PRODUCT_THUMBNAIL_REPLACE_COUNT_OF_DIGITS = 3
    const val PRODUCT_THUMBNAIL_REPLACE_TO_PREFIX = "https"
    const val SEARCH_MIN_LENGTH = 2

    // Database
    const val DB_VERSION = 1

    // Others
    const val CONDITION_NEW = "Nuevo"
    const val CONDITION_USED = "Usado"

    // Arguments
    const val ARGUMENT_PRODUCT_ID = "productId"
    const val ARGUMENT_PRODUCT_NAME = "productName"

}