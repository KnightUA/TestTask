package ua.knight.testtask.features.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.features.view.ui.fragments.user.UserDetailsFragmentArgs
import javax.inject.Inject

class UserDetailViewModel
@Inject constructor() : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun handleArgs(navArgs: UserDetailsFragmentArgs) {
        navArgs.user?.let { _user.postValue(it) }
    }
}