package ua.knight.testtask.data.repository

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ua.knight.testtask.features.model.user.User

interface UsersRxRepository {

    val compositeDisposable :  CompositeDisposable

    fun user() : Single<List<User>?>

    fun users(offset : Int) : Single<List<User>?>
}