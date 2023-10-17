package com.example.pract_1.network.dtos

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("status")
    val status: String = ""
)