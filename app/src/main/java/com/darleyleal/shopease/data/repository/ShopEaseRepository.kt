package com.darleyleal.shopease.data.repository

import android.content.Context
import com.darleyleal.shopease.data.dao.ItemDao
import com.darleyleal.shopease.data.database.ShopEaseDatabase
import com.darleyleal.shopease.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class ShopEaseRepository(context: Context) {
    private val dao: ItemDao = ShopEaseDatabase.getDatabase(context).itemDao()

    suspend fun insert(item: Item) = dao.insert(item)

    suspend fun update(item: Item) = dao.update(item)

    suspend fun delete(item: Item) = dao.delete(item)

    fun listItems(): Flow<List<Item>> = dao.getItems().flowOn(Dispatchers.IO).conflate()

    fun listPurshasedItems(): Flow<List<Item>> =
        dao.getPurshasedItems().flowOn(Dispatchers.IO).conflate()
}