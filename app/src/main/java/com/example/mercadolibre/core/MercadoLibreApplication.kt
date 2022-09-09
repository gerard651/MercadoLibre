package com.example.mercadolibre.core

import android.app.Application
import android.util.Log
import com.example.mercadolibre.core.Constants.LOG_ERROR_APPLICATION_TAG
import com.example.mercadolibre.data.entities.interfaces.IErrorLogger
import dagger.hilt.android.HiltAndroidApp
import kotlin.system.exitProcess

@HiltAndroidApp
class MercadoLibreApplication : Application(), IErrorLogger {

    override fun onCreate() {
        super.onCreate()
        logAllErrors()
    }

    /**
     * Log de todas las excepciones que pueden ocurrir durante la vida de la app
     * incluidas aquellas que la puedan hacer crashear
     */
    private fun logAllErrors() {
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            logError(paramThrowable.localizedMessage ?: "")
            exitProcess(2)
        }
    }

    override fun logError(errorMessage: String?) {
        Log.e(LOG_ERROR_APPLICATION_TAG, errorMessage ?: "")
    }

}