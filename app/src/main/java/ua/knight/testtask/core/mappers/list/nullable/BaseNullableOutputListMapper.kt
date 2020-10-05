package ua.knight.testtask.core.mappers.list.nullable

import ua.knight.testtask.core.mappers.Mapper

class BaseNullableOutputListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : NullableOutputListMapper<I, O> {
    override fun map(input: List<I>): List<O>? {
        return if (input.isEmpty()) null else input.map { mapper.map(it) }
    }
}
