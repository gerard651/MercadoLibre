package com.example.mercadolibre.data.helpers

import android.app.Dialog
import android.content.Context
import com.example.mercadolibre.R

object LoadingHelper {
    var loading: Dialog? = null

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