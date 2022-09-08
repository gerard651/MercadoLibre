package com.example.mercadolibre.data.helpers

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Crea un dialog full screen sin color de fondo
 * Utilizado mayormente para crear loadings o toasts
 * @param layout id del layout para crear el custom dialog
 */
fun Context.createDialogWithoutBackground(@LayoutRes layout: Int): Dialog {
    return (Dialog(this)).apply {
        setContentView(layout)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}


