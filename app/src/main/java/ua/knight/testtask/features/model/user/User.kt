package ua.knight.testtask.features.model.user

import ua.knight.testtask.core.glide.GlideImage
import java.io.Serializable

data class User(
    val id: String,
    val fullName: String,
    val image: GlideImage,
    val email: String,
    val phoneNumber : String,
    val age: Int,
    val gender: String,
    val dateOfBirthdayInMillis: Long
) : Serializable