package com.example.pract_1.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pract_1.tables.DrugTable
import com.example.pract_1.tables.PharmacyTable
import com.example.pract_1.tables.ProfileTable

@Database(entities = [DrugTable::class, PharmacyTable::class, ProfileTable::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun getBasketDao(): Dao

}