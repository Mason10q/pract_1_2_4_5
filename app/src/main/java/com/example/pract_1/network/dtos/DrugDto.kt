package com.example.pract_1.network.dtos

import com.google.gson.annotations.SerializedName

data class DrugDto(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("drug_name")
    val name: String = ""
)