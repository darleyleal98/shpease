package com.darleyleal.shopease.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "isChecked")
    val isChecked: Boolean? = null,

    @ColumnInfo(name = "dateTime")
    val dateTime: String? = null
)