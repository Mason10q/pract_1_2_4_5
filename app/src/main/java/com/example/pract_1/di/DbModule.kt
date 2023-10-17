package com.example.pract_1.di

import androidx.room.Room
import com.example.pract_1.db.Dao
import com.example.pract_1.db.DataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context

@Module
class DbModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideBasketDataBase() = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        "basket_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(dataBase: DataBase) = dataBase.getBasketDao()

}