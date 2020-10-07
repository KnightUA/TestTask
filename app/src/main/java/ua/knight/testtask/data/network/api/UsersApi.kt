package ua.knight.testtask.data.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ua.knight.testtask.features.gson.user.UsersResponse

interface UsersApi {

    @GET("api")
    fun user(): Call<UsersResponse>

    @GET("api")
    fun users(@Query("results") offset: Int): Call<UsersResponse>

    @GET("api")
    fun users(
        @Query("results") offset: Int,
        @Query("page") page: Int
    ): Call<UsersResponse>
}