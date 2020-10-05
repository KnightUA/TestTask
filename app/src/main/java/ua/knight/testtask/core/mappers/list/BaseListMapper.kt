package ua.knight.testtask.core.mappers.list

import ua.knight.testtask.core.mappers.Mapper

class BaseListMapper<I, O>(private val mapper: Mapper<I, O>) : ListMapper<I, O> {

    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}
