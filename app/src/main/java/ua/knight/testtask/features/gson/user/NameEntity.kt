package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class NameEntity(
    @SerializedName("title") val title : String,
    @SerializedName("first") val first : String,
    @SerializedName("last") val last : String
) {
    override fun toString(): String {
        val fullNameBuilder = StringBuilder(first).append(' ').append(last)
        return fullNameBuilder.toString()
    }
}