package ua.knight.testtask.core.mappers

class BaseNullableMapper<I,O> (private val mapper: Mapper<I, O>
) : NullableMapper<I, O> {
    override fun map(input: I?): O? {
        return input?.let { mapper.map(it) }
    }
}