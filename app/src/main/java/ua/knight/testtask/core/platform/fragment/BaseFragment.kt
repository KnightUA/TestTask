package ua.knight.testtask.core.platform.fragment

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ua.knight.testtask.application.AndroidApplication
import ua.knight.testtask.core.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    open fun progressStatus(viewStatus: Int) {
        // handle viewStatus for render progress
    }
}