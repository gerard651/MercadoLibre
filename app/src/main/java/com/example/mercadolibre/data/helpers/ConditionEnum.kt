package com.example.mercadolibre.data.helpers

import com.example.mercadolibre.core.Constants.CONDITION_NEW
import com.example.mercadolibre.core.Constants.CONDITION_USED

enum class ConditionEnum(val textToShow: String) {
    NEW(CONDITION_NEW),
    USED(CONDITION_USED)
}