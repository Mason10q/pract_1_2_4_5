package com.example.pract_1.network

import com.example.pract_1.network.dtos.DrugDto
import com.example.pract_1.network.dtos.PharmacyDto
import com.example.pract_1.network.dtos.ProfileDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PharmacyApi {

    @GET("/pharmacies")
    fun getPharmacyList(): Single<List<PharmacyDto>>


    @GET("/profile")
    fun getProfile(): Single<ProfileDto>

    @GET("/drugs")
    fun getDrugsList(): Single<List<DrugDto>>
}