package com.example.mercadolibre.data.helpers

import android.util.Log
import com.example.mercadolibre.core.Constants
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger

class ErrorLogger : IErrorLogger {

    override fun logError(errorMessage: String?) {
        Log.e(Constants.LOG_ERROR_TAG, errorMessage ?: "")
    }

}