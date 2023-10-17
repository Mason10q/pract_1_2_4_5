package com.example.pract_1.mappers

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import com.example.pract_1.Mapper
import com.example.pract_1.databinding.ProfileBinding
import com.example.pract_1.entities.Profile
import com.example.pract_1.network.dtos.ProfileDto
import com.example.pract_1.tables.ProfileTable
import javax.inject.Inject

class ProfileMapper @Inject constructor(): Mapper<ProfileDto, ProfileTable, Profile> {
    override fun map(item: ProfileDto): Profile = Profile(
        item.name,
        item.status
    )

    override fun mapTable(item: ProfileTable): Profile = Profile(
        item.name,
        item.status
    )

    override fun mapDtoToTable(item: ProfileDto): ProfileTable = ProfileTable(
        name = item.name,
        status = item.status
    )

}