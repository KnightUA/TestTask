package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("id") val idEntity: IdEntity,
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val nameEntity: NameEntity,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val pictureEntity: PictureEntity
)