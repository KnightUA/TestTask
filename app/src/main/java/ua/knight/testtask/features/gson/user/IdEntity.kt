package ua.knight.testtask.features.gson.user

import com.google.gson.annotations.SerializedName

data class IdEntity(
    @SerializedName("name") val name : String,
    @SerializedName("value") val value : String
) {
    override fun toString(): String {
        return name + value
    }
}