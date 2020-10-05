package ua.knight.testtask.core.di

import dagger.Component
import ua.knight.testtask.core.di.viewmodel.ViewModelModule
import ua.knight.testtask.application.AndroidApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}