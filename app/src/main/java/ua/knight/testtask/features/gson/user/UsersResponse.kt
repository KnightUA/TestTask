package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("results") val usersEntities: List<UserEntity>?
)