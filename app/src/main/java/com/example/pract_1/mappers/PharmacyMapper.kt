package com.example.pract_1.mappers

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.pract_1.Mapper
import com.example.pract_1.entities.Pharmacy
import com.example.pract_1.network.dtos.PharmacyDto
import com.example.pract_1.tables.PharmacyTable
import javax.inject.Inject

class PharmacyMapper @Inject constructor(): Mapper<PharmacyDto, PharmacyTable, Pharmacy> {
    override fun map(item: PharmacyDto): Pharmacy = Pharmacy(item.name)
    override fun mapTable(item: PharmacyTable): Pharmacy = Pharmacy(item.name)

    override fun mapDtoToTable(item: PharmacyDto): PharmacyTable = PharmacyTable(name = item.name)
}