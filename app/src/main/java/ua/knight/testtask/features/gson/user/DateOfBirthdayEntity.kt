package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class DateOfBirthdayEntity(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)