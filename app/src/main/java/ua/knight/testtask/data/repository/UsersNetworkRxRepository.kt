package ua.knight.testtask.data.repository

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.knight.testtask.core.platform.NetworkHandler
import ua.knight.testtask.data.network.service.UsersService
import ua.knight.testtask.features.gson.user.UsersResponse
import ua.knight.testtask.features.mappers.user.UserMappers
import ua.knight.testtask.features.model.user.User
import javax.inject.Inject

class UsersNetworkRxRepository
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: UsersService
) : UsersRxRepository {

    override fun user(): Single<User?> {
        return Single.create { emitter ->
            if (networkHandler.isConnected)
                service.user().enqueue(object : Callback<UsersResponse> {
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        if (response.isSuccessful) {
                            val usersResponse = response.body()

                            if (usersResponse == null)
                                emitter.onError(Exception("Users response is null!"))
                            else
                                emitter.onSuccess(
                                    UserMappers.createNullableInputMapperList()
                                        .map(usersResponse.usersEntities).first()
                                )
                        } else {
                            emitter.onError(Exception("Something went wrong!"))
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
            else
                emitter.onError(Exception("Lost network connection!"))
        }
    }

    override fun users(offset: Int): Single<List<User>?> {
        return Single.create { emitter ->
            if (networkHandler.isConnected)
                service.users(offset).enqueue(object : Callback<UsersResponse> {
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        if (response.isSuccessful) {
                            val usersResponse = response.body()

                            if (usersResponse == null)
                                emitter.onError(Exception("Users response is null!"))
                            else
                                emitter.onSuccess(
                                    UserMappers.createNullableInputMapperList()
                                        .map(usersResponse.usersEntities)
                                )
                        } else {
                            emitter.onError(Exception("Something went wrong!"))
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
            else
                emitter.onError(Exception("Lost network connection!"))
        }
    }

    override fun users(offset: Int, page: Int): Single<List<User>?> {
        return Single.create { emitter ->
            if (networkHandler.isConnected) {
                service.users(offset, page).enqueue(object : Callback<UsersResponse> {
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        if (response.isSuccessful) {
                            val usersResponse = response.body()

                            if (usersResponse == null)
                                emitter.onError(Exception("Users response is null!"))
                            else
                                emitter.onSuccess(
                                    UserMappers.createNullableInputMapperList()
                                        .map(usersResponse.usersEntities)
                                )
                        } else {
                            emitter.onError(Exception("Something went wrong!"))
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
            } else
                emitter.onError(Exception("Lost network connection!"))
        }
    }
}