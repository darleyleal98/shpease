package com.darleyleal.shopease.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darleyleal.shopease.data.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items WHERE isChecked = 0")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE isChecked = 1")
    fun getPurshasedItems(): Flow<List<Item>>
}