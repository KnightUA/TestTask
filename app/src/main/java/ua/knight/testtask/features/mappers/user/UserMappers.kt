package ua.knight.testtask.features.mappers.user

import ua.knight.testtask.core.glide.GlideImageUrl
import ua.knight.testtask.core.mappers.Mapper
import ua.knight.testtask.core.mappers.list.nullable.BaseNullableInputListMapper
import ua.knight.testtask.features.gson.user.UserEntity
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.utils.DateTimeUtils

object UserMappers {

    fun createMapper(): Mapper<UserEntity, User> = object : Mapper<UserEntity, User> {
        override fun map(input: UserEntity): User {
            return User(
                input.idEntity.toString(),
                input.nameEntity.toString(),
                GlideImageUrl(input.pictureEntity.mediumUrl),
                input.email,
                input.phone,
                input.dateOfBirthdayEntity.age,
                input.gender,
                DateTimeUtils.parseIntoMillis(input.dateOfBirthdayEntity.date)
            )
        }
    }

    fun createNullableInputMapperList(): BaseNullableInputListMapper<UserEntity, User> {
        return BaseNullableInputListMapper(createMapper())
    }
}