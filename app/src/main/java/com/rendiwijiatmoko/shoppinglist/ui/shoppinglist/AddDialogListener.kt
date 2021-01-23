package com.rendiwijiatmoko.shoppinglist.ui.shoppinglist

import com.rendiwijiatmoko.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonCliked (item:ShoppingItem)
}