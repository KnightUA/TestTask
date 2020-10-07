package ua.knight.testtask.core.platform.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ua.knight.testtask.R

abstract class BaseNavigationActivity : AppCompatActivity(), NavigationActivity {

    protected lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseInit()
        initNavController()
    }

    override fun onBackPressed() {
        if (::mNavController.isInitialized) {
            if (!mNavController.navigateUp()) {
                finish()
            }
        } else {
            if (supportFragmentManager.backStackEntryCount <= 1) {
                finish()
            }

            super.onBackPressed()
        }
    }

    override fun initNavController() {
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) is NavHostFragment) {
            mNavController =
                (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

            onInitNavController()
        }
    }

    abstract fun baseInit()

    /**
     * called when initialization of nav controller was successful
     * you can do anything with NavController and don't get any exceptions
     */
    open fun onInitNavController() {
        //empty
    }
}