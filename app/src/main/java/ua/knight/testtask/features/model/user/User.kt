package ua.knight.testtask.features.model.user

import ua.knight.testtask.core.glide.GlideImage

data class User(val id: String, val fullName: String, val email: String, val age : Int, val image: GlideImage)