package ua.knight.testtask.core.mappers.list.nullable

import ua.knight.testtask.core.mappers.Mapper

class BaseNullableInputListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : NullableInputListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}
