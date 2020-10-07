package ua.knight.testtask.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ua.knight.testtask.features.viewmodel.user.UserDetailViewModel
import ua.knight.testtask.features.viewmodel.user.UsersViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun bindUsersViewModel(viewModel : UsersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun bindUserDetailViewModel(viewModel : UserDetailViewModel) : ViewModel
}