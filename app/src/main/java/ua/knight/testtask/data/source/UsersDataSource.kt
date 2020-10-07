package ua.knight.testtask.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.knight.testtask.data.repository.UsersRxRepository
import ua.knight.testtask.features.model.loading.State
import ua.knight.testtask.features.model.user.User
import javax.inject.Inject

class UsersDataSource
@Inject constructor(
    private val repository: UsersRxRepository,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<User>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User>) {
        val page = params.startPosition / params.loadSize

        compositeDisposable.add(
            repository.users(params.loadSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSubscribe { updateState(State.LOADING) }
                .subscribe({ users ->
                    if (users == null) updateState(State.ERROR)
                    else {
                        callback.onResult(users)
                        updateState(State.DONE)
                    }
                }, {
                    Timber.e(it)
                    updateState(State.ERROR)
                    setRetry(Action { loadRange(params, callback) })
                })
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User>) {
        compositeDisposable.add(
            repository.users(params.pageSize, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSubscribe { updateState(State.LOADING) }
                .subscribe({ users ->
                    if (users == null) updateState(State.ERROR)
                    else {
                        callback.onResult(users, params.requestedStartPosition)
                        updateState(State.DONE)
                    }
                }, {
                    Timber.e(it)
                    updateState(State.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                })
        )
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}
 