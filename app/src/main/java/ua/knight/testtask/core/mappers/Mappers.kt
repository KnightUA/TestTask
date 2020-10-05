package ua.knight.testtask.core.mappers

import ua.knight.testtask.core.mappers.list.BaseListMapper
import ua.knight.testtask.core.mappers.list.nullable.BaseNullableInputListMapper
import ua.knight.testtask.core.mappers.list.nullable.BaseNullableOutputListMapper

interface Mappers<I, O> {

    val mapper: Mapper<I, O>

    fun createMapper(): Mapper<I, O> {
        return mapper
    }

    fun createNullableMapper() : Mapper<I?, O?> {
        return BaseNullableMapper(createMapper())
    }

    fun createMapperList(): BaseListMapper<I, O> {
        return BaseListMapper(createMapper())
    }

    fun createNullableInputMapperList(): BaseNullableInputListMapper<I, O> {
        return BaseNullableInputListMapper(createMapper())
    }

    fun createNullableOutputMapperList(): BaseNullableOutputListMapper<I, O> {
        return BaseNullableOutputListMapper(createMapper())
    }

}