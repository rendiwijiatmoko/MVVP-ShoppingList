package com.rendiwijiatmoko.shoppinglist.data.repositories

import com.rendiwijiatmoko.shoppinglist.data.db.ShoppingDatabase
import com.rendiwijiatmoko.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db:ShoppingDatabase
) {
    suspend fun upsert(item:ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getALlShoppingItems()
}