package ua.knight.testtask.application

import android.app.Application
import android.content.Context
import timber.log.Timber
import ua.knight.testtask.BuildConfig
import ua.knight.testtask.core.di.ApplicationComponent
import ua.knight.testtask.core.di.ApplicationModule
import ua.knight.testtask.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    @Suppress("DEPRECATION")
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        this.injectMembers()
        this.initializeTimber()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}