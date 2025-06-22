package com.darleyleal.shopease.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteDatabase
import com.darleyleal.shopease.data.dao.ItemDao
import com.darleyleal.shopease.data.model.Item
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Item::class], version = 3)
abstract class ShopEaseDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private lateinit var INSTANCE: ShopEaseDatabase

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ShopEaseDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(ShopEaseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ShopEaseDatabase::class.java,
                        "items"
                    ).addMigrations(migration)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val migration = object: Migration(1, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
               db.execSQL("ALTER TABLE items ADD COLUMN dateTime TEXT")
            }
        }
    }
}