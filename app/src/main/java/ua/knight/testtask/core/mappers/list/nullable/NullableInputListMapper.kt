package ua.knight.testtask.core.mappers.list.nullable

import ua.knight.testtask.core.mappers.Mapper

interface NullableInputListMapper<I, O>:
    Mapper<List<I>?, List<O>>
