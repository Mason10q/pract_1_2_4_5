package com.example.pract_1.mappers

import com.example.pract_1.Mapper
import com.example.pract_1.entities.Drug
import com.example.pract_1.network.dtos.DrugDto
import com.example.pract_1.tables.DrugTable
import javax.inject.Inject

class DrugMapper @Inject constructor(): Mapper<DrugDto, DrugTable, Drug> {
    override fun map(item: DrugDto): Drug = Drug(item.name)
    override fun mapTable(item: DrugTable): Drug = Drug(item.name)

    override fun mapDtoToTable(item: DrugDto) = DrugTable(name = item.name)
}