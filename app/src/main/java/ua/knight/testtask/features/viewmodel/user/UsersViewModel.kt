package ua.knight.testtask.features.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.knight.testtask.data.repository.UsersRxRepository
import ua.knight.testtask.features.model.user.User
import javax.inject.Inject

class UsersViewModel
@Inject constructor(private val repository: UsersRxRepository) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    fun loadUsers() {
        repository.compositeDisposable.add(
            repository.users(20).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe({ _users.postValue(it) }, {
                    Timber.e(it)
                })
        )
    }
}