package ua.knight.testtask.core.platform.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import ua.knight.testtask.R

abstract class BaseNavigationFragment : BaseFragment(), NavigationFragment {

    protected lateinit var mNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavController()
    }

    protected fun initNavController() {
        activity?.let {
            mNavController = Navigation.findNavController(it, R.id.fragmentContainerView)
        }
    }

    override fun openScreen(actionId: Int) {
        if (::mNavController.isInitialized)
            mNavController.navigate(actionId)
    }

    override fun openScreen(navDirections: NavDirections) {
        if (::mNavController.isInitialized)
            mNavController.navigate(navDirections)
    }
}