package com.example.mercadolibre.data.entities.exceptions

import com.example.mercadolibre.core.Constants.EXCEPTION_PRODUCT_NOT_FOUND

class ProductByNameNotFoundException : Exception(EXCEPTION_PRODUCT_NOT_FOUND)