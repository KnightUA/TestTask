package ua.knight.testtask.features.view.ui.fragments.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import ua.knight.testtask.core.extentions.observe
import ua.knight.testtask.core.extentions.viewModel
import ua.knight.testtask.core.platform.fragment.BaseFragment
import ua.knight.testtask.core.platform.fragment.BaseNavigationFragment
import ua.knight.testtask.databinding.FragmentUsersBinding
import ua.knight.testtask.features.model.loading.State
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.features.view.adapter.paging.user.UserPlAdapter
import ua.knight.testtask.features.viewmodel.user.UsersViewModel

class UsersFragment : BaseNavigationFragment() {

    lateinit var mViewModel: UsersViewModel
    private lateinit var mBinding: FragmentUsersBinding

    private val mAdapter: UserPlAdapter by lazy {
        return@lazy UserPlAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mViewModel = viewModel(viewModelFactory) {
            observe(users, ::renderUsers)
            observe(getState(), ::renderState)
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
    }

    private fun initializeView() {
        mBinding.buttonRetry.setOnClickListener { mViewModel.retry() }
        mBinding.recyclerViewUsers.adapter = mAdapter.apply {
            retry = { mViewModel.retry() }
            click = { user ->
                val directions = UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment().setUser(user)
                openScreen(directions)
            }
        }
    }

    private fun renderState(state: State?) {
        mBinding.progressBar.visibility = if (mViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
        mBinding.layoutError.visibility = if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE

        if (!mViewModel.listIsEmpty()) {
            mAdapter.setState(state ?: State.DONE)
        }
    }

    private fun renderUsers(users: PagedList<User>?) {
        mAdapter.submitList(users)
    }
}