package ua.knight.testtask.features.mappers.user

import ua.knight.testtask.core.glide.GlideImageUrl
import ua.knight.testtask.core.mappers.Mapper
import ua.knight.testtask.core.mappers.list.nullable.BaseNullableInputListMapper
import ua.knight.testtask.features.gson.user.UserEntity
import ua.knight.testtask.features.model.user.User

object UserMappers {

    fun createMapper(): Mapper<UserEntity, User> = object : Mapper<UserEntity, User> {
        override fun map(input: UserEntity): User {
            return User(
                input.idEntity.toString(),
                input.nameEntity.toString(),
                input.email,
                input.dateOfBirthdayEntity.age,
                GlideImageUrl(input.pictureEntity.mediumUrl)
            )
        }
    }

    fun createNullableInputMapperList(): BaseNullableInputListMapper<UserEntity, User> {
        return BaseNullableInputListMapper(createMapper())
    }
}