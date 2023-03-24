package com.ozgursarki.shoppinglist.data.di

import android.content.Context
import androidx.room.Room
import com.ozgursarki.shoppinglist.data.local.ShoppingDatabase
import com.ozgursarki.shoppinglist.data.local.ShoppingItemDAO
import com.ozgursarki.shoppinglist.data.local.ShoppingListDAO
import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingDatabase::class.java,
            "shopping_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceRepository(
        shoppingListDao: ShoppingListDAO,
        shoppingItemDao: ShoppingItemDAO
    ): ShoppingRepository {
        return ShoppingRepository(shoppingListDao, shoppingItemDao)
    }

    @Provides
    @Singleton
    fun provideShoppingItemDao(shoppingdb: ShoppingDatabase): ShoppingItemDAO {
        return shoppingdb.shoppingItemDao()
    }

    @Provides
    @Singleton
    fun provideListDao(shoppingdb: ShoppingDatabase): ShoppingListDAO {
        return shoppingdb.shoppingListDao()
    }

}