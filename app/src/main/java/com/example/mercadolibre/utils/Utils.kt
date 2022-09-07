package com.example.mercadolibre.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun hideViews(vararg views: View){
    views.forEach { it.visibility = View.GONE }
}

fun showViews(vararg views: View){
    views.forEach { it.visibility = View.VISIBLE }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.toggleVisibility(show: Boolean) {
    if(show)
        show()
    else
        hide()
}

fun Context.createDialogFullScreen(@LayoutRes layout: Int): Dialog {
    val dialog = Dialog(this)
    dialog.apply {
        setContentView(layout);
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setCancelable(false);
    }
    return dialog
}

fun visibilityViews(vararg views: View, visibility: Int){
    views.forEach { it.visibility = visibility }
}

fun RecyclerView.init(context: Context){
    apply {
        layoutManager = LinearLayoutManager(context)
        isNestedScrollingEnabled = false
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
    }
}

fun Context.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun String.parseThumbailUrl(): String {
    return this.replaceRange(0..3, "https")
}


