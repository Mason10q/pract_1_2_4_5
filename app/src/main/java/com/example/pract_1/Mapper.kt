package com.example.pract_1

interface Mapper<DTO: Any, TABLE: Any, E: Any> {

    fun map(item: DTO): E

    fun mapTable(item: TABLE): E

    fun mapDtoToTable(item: DTO): TABLE

    fun mapTable(items: List<TABLE>): List<E> = items.map(this::mapTable)

    fun mapDtoToTable(items: List<DTO>): List<TABLE> = items.map(::mapDtoToTable)
}