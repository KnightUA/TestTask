package ua.knight.testtask.data.network.service

import retrofit2.Call
import retrofit2.Retrofit
import ua.knight.testtask.data.network.api.UsersApi
import ua.knight.testtask.features.gson.user.UsersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersService
@Inject constructor(retrofit: Retrofit) : UsersApi {

    private val usersApi by lazy { retrofit.create(UsersApi::class.java) }

    override fun user(): Call<UsersResponse> = usersApi.user()

    override fun users(offset: Int): Call<UsersResponse> = usersApi.users(offset)

    override fun users(offset: Int, page: Int): Call<UsersResponse> = usersApi.users(offset, page)
}