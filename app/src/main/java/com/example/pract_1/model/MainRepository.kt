package com.example.pract_1.model

import android.util.Log
import com.example.pract_1.Mapper
import com.example.pract_1.db.Dao
import com.example.pract_1.entities.Drug
import com.example.pract_1.entities.Pharmacy
import com.example.pract_1.entities.Profile
import com.example.pract_1.network.PharmacyApi
import com.example.pract_1.network.dtos.DrugDto
import com.example.pract_1.network.dtos.PharmacyDto
import com.example.pract_1.network.dtos.ProfileDto
import com.example.pract_1.tables.DrugTable
import com.example.pract_1.tables.PharmacyTable
import com.example.pract_1.tables.ProfileTable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: PharmacyApi,
    private val dao: Dao,
    private val pharmacyMapper: Mapper<PharmacyDto, PharmacyTable, Pharmacy>,
    private val drugMapper: Mapper<DrugDto, DrugTable, Drug>,
    private val profileMapper: Mapper<ProfileDto, ProfileTable, Profile>
) {

    private fun insertDrugs(drugs: List<DrugTable>) =
        dao.insertIntoDrugs(drugs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getDrugsFromDb() =
        dao.getDrugs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(drugMapper::mapTable)


    private fun insertPharmacies(pharmacies: List<PharmacyTable>) =
        dao.insertIntoPharmacies(pharmacies)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getPharmaciesFromDb() =
        dao.getPharmacies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(pharmacyMapper::mapTable)


    private fun insertProfile(profile: ProfileTable) =
        dao.insertIntoProfile(profile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getProfileFromDb() =
        dao.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(profileMapper::mapTable)


    fun getDrugs() =
        api.getDrugsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(drugMapper::mapDtoToTable)
            .flatMap { drugs ->
                insertDrugs(drugs).toSingleDefault(drugs)
            }
            .flatMap {
                getDrugsFromDb()
            }

    fun getPharmacies() =
        api.getPharmacyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(pharmacyMapper::mapDtoToTable)
            .flatMap { pharmacies ->
                insertPharmacies(pharmacies).toSingleDefault(pharmacies)
            }
            .flatMap {
                getPharmaciesFromDb()
            }

    fun getProfile() =
        api.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(profileMapper::mapDtoToTable)
            .flatMap { profile ->
                insertProfile(profile).toSingleDefault(profile)
            }
            .flatMap {
                getProfileFromDb()
            }

}