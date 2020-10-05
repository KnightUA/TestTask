package ua.knight.testtask.core.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}
