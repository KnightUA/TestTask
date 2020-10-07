package ua.knight.testtask.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import ua.knight.testtask.data.repository.UsersRxRepository
import ua.knight.testtask.features.model.user.User
import javax.inject.Inject

class UsersSourceFactory
constructor(
    private val repository: UsersRxRepository,
    private val compositeDisposable: CompositeDisposable
) :
    DataSource.Factory<Int, User>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, User> {
        val userDataSource = UsersDataSource(repository, compositeDisposable)
        usersDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}
 