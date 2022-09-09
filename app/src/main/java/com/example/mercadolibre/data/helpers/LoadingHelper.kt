package com.example.mercadolibre.data.helpers

import android.app.Dialog
import android.content.Context
import com.example.mercadolibre.R

/**
 * Clase encargada de manejar el loading en toda la app
 */
object LoadingHelper {
    private var loading: Dialog? = null

    /**
     * @param isLoading indica si el loading debe ser mostrado
     */
    fun showOrHideLoading(context: Context, isLoading: Boolean) {
        if(isLoading) {
            create(context)
            show()
        } else {
            hide()
        }
    }

    private fun create(context: Context) {
        loading = context.createDialogWithoutBackground(R.layout.loading_component)
    }

    private fun show() {
        loading?.show()
    }

    private fun hide() {
        loading?.dismiss()
    }

}