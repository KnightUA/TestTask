package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("id") val idEntity: IdEntity,
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val nameEntity: NameEntity,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("dob") val dateOfBirthdayEntity: DateOfBirthdayEntity,
    @SerializedName("picture") val pictureEntity: PictureEntity
)