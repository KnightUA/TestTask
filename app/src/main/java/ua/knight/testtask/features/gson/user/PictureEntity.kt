package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class PictureEntity(
    @SerializedName("large") val largeUrl: String,
    @SerializedName("medium") val mediumUrl: String,
    @SerializedName("thumbnail") val thumbnailUrl: String
)