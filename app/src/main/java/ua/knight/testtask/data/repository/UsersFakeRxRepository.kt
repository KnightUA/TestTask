package ua.knight.testtask.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ua.knight.testtask.features.gson.user.UsersResponse
import ua.knight.testtask.features.mappers.user.UserMappers
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.utils.AssetsUtils
import java.lang.reflect.Type
import javax.inject.Inject

class UsersFakeRxRepository
@Inject constructor(private val applicationContext: Context) : UsersRxRepository {

    override val compositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    override fun user(): Single<List<User>?> {
        return Single.create { emitter ->
            try {
                val jsonFileString = AssetsUtils.fetchJsonFrom(applicationContext, "user.json")
                val gson = Gson()
                val type: Type = object : TypeToken<UsersResponse?>() {}.type

                val response = gson.fromJson<UsersResponse?>(jsonFileString, type)

                if (response == null)
                    emitter.onError(Exception("Users response is null!"))
                else
                    emitter.onSuccess(
                        UserMappers.createNullableInputMapperList().map(response.usersEntities)
                    )
            } catch (exception: JsonSyntaxException) {
                emitter.onError(exception)
            }
        }
    }

    override fun users(offset: Int): Single<List<User>?> {
        return Single.create { emitter ->
            try {
                val jsonFileString = AssetsUtils.fetchJsonFrom(applicationContext, "users.json")
                val gson = Gson()
                val type: Type = object : TypeToken<UsersResponse?>() {}.type

                val response = gson.fromJson<UsersResponse?>(jsonFileString, type)

                if (response == null)
                    emitter.onError(Exception("Users response is null!"))
                else {
                    val usersSize = response.usersEntities?.size ?: 0
                    if (offset >= usersSize)
                        emitter.onError(Exception("Offset of users much greater then size!"))
                    else
                        emitter.onSuccess(
                            UserMappers.createNullableInputMapperList()
                                .map(response.usersEntities?.subList(0, offset))
                        )
                }
            } catch (exception: JsonSyntaxException) {
                emitter.onError(exception)
            }
        }
    }
}