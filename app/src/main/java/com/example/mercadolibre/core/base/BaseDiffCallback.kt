package com.example.mercadolibre.core.base

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallback<T>(
    private val listOld: ArrayList<T>,
    private val listNew: ArrayList<T>
) : DiffUtil.Callback() {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listOld[oldItemPosition] == listNew[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listOld[oldItemPosition].hashCode() == listNew[newItemPosition].hashCode()
    }

    override fun getNewListSize() = listNew.size

    override fun getOldListSize() = listOld.size

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}