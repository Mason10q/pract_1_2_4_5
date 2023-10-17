package com.example.pract_1.network.dtos

import com.google.gson.annotations.SerializedName

data class PharmacyDto(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("pharmacy_name")
    val name: String = ""
)