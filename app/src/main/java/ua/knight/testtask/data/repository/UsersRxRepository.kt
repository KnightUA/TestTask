package ua.knight.testtask.data.repository

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ua.knight.testtask.features.model.user.User

interface UsersRxRepository {
    fun user(): Single<User?>
    fun users(offset: Int): Single<List<User>?>
    fun users(offset: Int, page: Int): Single<List<User>?>
}