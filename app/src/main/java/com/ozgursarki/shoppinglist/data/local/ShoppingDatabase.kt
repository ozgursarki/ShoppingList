package com.ozgursarki.shoppinglist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity

@Database(entities = [ShoppingListEntity::class, ShoppingItemEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDAO
    abstract fun shoppingItemDao(): ShoppingItemDAO

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "shopping_db"
            ).build()
    }
}