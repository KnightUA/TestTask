package ua.knight.testtask.features.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import ua.knight.testtask.data.repository.UsersRxRepository
import ua.knight.testtask.data.source.UsersDataSource
import ua.knight.testtask.data.source.UsersSourceFactory
import ua.knight.testtask.features.model.loading.State
import ua.knight.testtask.features.model.user.User
import javax.inject.Inject

class UsersViewModel
@Inject constructor(private val repository: UsersRxRepository) : ViewModel() {

    var users: LiveData<PagedList<User>>

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: UsersSourceFactory = UsersSourceFactory(repository, compositeDisposable)

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        users = LivePagedListBuilder<Int, User>(sourceFactory, config).build()
    }

    fun retry() {
        sourceFactory.usersDataSourceLiveData.value?.retry()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<UsersDataSource,
            State>(sourceFactory.usersDataSourceLiveData, UsersDataSource::state)

    fun listIsEmpty(): Boolean {
        return users.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}