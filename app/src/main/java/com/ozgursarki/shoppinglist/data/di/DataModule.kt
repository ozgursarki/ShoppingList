package com.ozgursarki.shoppinglist.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ozgursarki.shoppinglist.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

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
    fun provideShoppingRepository(
        shoppingListDao: ShoppingListDAO,
        shoppingItemDao: ShoppingItemDAO,
        defaultPreferences: DefaultPreferences
    ): ShoppingRepository {
        return ShoppingRepository(shoppingListDao, shoppingItemDao, defaultPreferences)
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

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): DefaultPreferences {
        return DefaultPreferences(sharedPreferences)
    }

}