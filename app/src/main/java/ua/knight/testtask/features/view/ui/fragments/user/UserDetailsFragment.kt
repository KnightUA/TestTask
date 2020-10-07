package ua.knight.testtask.features.view.ui.fragments.user

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import ua.knight.testtask.R
import ua.knight.testtask.core.extentions.observe
import ua.knight.testtask.core.extentions.viewModel
import ua.knight.testtask.core.platform.fragment.BaseFragment
import ua.knight.testtask.databinding.FragmentUserDetailsBinding
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.features.viewmodel.user.UserDetailViewModel


class UserDetailsFragment : BaseFragment() {

    lateinit var mViewModel: UserDetailViewModel
    private lateinit var mBinding: FragmentUserDetailsBinding

    private val mArgs: UserDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mViewModel = viewModel(viewModelFactory) {
            observe(user, ::renderUser)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onOpenCallApp()
                } else {
                    showPermissionsDeniedInfo()
                }
            }
        }
    }

    private fun initializeView() {
        mViewModel.handleArgs(mArgs)
        mBinding.buttonCall.setOnClickListener { onOpenCallApp() }
    }

    protected fun renderUser(user: User?) {
        mBinding.model = user
    }

    private fun onOpenCallApp() {
        when {
            permissionGranted() -> {
                openCallApp()
            }
            else -> {
                requestPermissions()
            }
        }
    }

    private fun permissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(android.Manifest.permission.CALL_PHONE),
            PERMISSIONS_REQUEST_CODE
        )
    }

    private fun showPermissionsDeniedInfo() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error_permission_phone_denied),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun openCallApp() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:${mBinding.model?.phoneNumber}")
        startActivity(intent)
    }

    companion object {
        const val PERMISSIONS_REQUEST_CODE = 10001
    }
}