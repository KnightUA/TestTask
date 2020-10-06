package ua.knight.testtask.features.view.ui.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.knight.testtask.core.extentions.observe
import ua.knight.testtask.core.extentions.viewModel
import ua.knight.testtask.core.platform.BaseFragment
import ua.knight.testtask.databinding.FragmentUsersBinding
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.features.view.adapter.recycler.UsersRvAdapter
import ua.knight.testtask.features.viewmodel.user.UsersViewModel

class UsersFragment : BaseFragment() {

    lateinit var mViewModel: UsersViewModel
    private lateinit var mBinding: FragmentUsersBinding

    private val mAdapter: UsersRvAdapter by lazy {
        return@lazy UsersRvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mViewModel = viewModel(viewModelFactory) {
            observe(users, ::renderUsers)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadUsersList()
    }

    private fun initializeView() {
        mBinding.recyclerViewUsers.adapter = mAdapter
    }

    private fun loadUsersList() {
        mViewModel.loadUsers()
    }

    private fun renderUsers(users: List<User>?) {
        mAdapter.clearAndAddAll(users)
    }
}