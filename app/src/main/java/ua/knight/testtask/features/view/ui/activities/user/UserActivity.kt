package ua.knight.testtask.features.view.ui.activities.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.NavigationUI
import ua.knight.testtask.R
import ua.knight.testtask.core.platform.activity.BaseNavigationActivity
import ua.knight.testtask.databinding.ActivityUserBinding

class UserActivity : BaseNavigationActivity() {

    private lateinit var mBinding: ActivityUserBinding

    override fun baseInit() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        setSupportActionBar(mBinding.toolbar)
        mBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onInitNavController() {
        NavigationUI.setupActionBarWithNavController(this, mNavController)
    }
}