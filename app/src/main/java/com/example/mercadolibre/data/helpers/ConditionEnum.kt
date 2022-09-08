package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.core.Constants.CONDITION_NEW
import com.example.mercadolibre.core.Constants.CONDITION_USED

/**
 * Clase que representa los tipos de condiciones
 * en la que se encuentra un producto
 */
enum class ConditionEnum(val textToShow: String) {
    NEW(CONDITION_NEW),
    USED(CONDITION_USED)
}