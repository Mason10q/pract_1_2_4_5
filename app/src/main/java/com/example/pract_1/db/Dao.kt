package com.example.pract_1.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pract_1.entities.Pharmacy
import com.example.pract_1.tables.DrugTable
import com.example.pract_1.tables.PharmacyTable
import com.example.pract_1.tables.ProfileTable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoDrugs(drugs: List<DrugTable>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoPharmacies(pharmacies: List<PharmacyTable>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoProfile(profile: ProfileTable): Completable

    @Query("select * from PharmacyTable")
    fun getPharmacies(): Single<List<PharmacyTable>>

    @Query("select * from DrugTable")
    fun getDrugs(): Single<List<DrugTable>>

    @Query("select * from ProfileTable ")
    fun getProfile(): Single<ProfileTable>


}