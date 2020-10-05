package ua.knight.testtask.features.view.ui.activities.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ua.knight.testtask.R
import ua.knight.testtask.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
    }

}