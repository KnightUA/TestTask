package ua.knight.testtask.features.view.adapter.paging.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.knight.testtask.databinding.ItemLoadingBinding
import ua.knight.testtask.databinding.ItemUserBinding
import ua.knight.testtask.features.model.loading.Loading
import ua.knight.testtask.features.model.loading.State
import ua.knight.testtask.features.model.user.User
import ua.knight.testtask.features.view.adapter.holders.base.LoadingViewHolder
import ua.knight.testtask.features.view.adapter.holders.user.UserViewHolder
import java.lang.ref.WeakReference
import javax.inject.Inject

class UserPlAdapter
@Inject constructor() :
    PagedListAdapter<User, RecyclerView.ViewHolder>(diffUtilCallback) {

    companion object {
        private val diffUtilCallback = object :
            DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    internal var click: (User?) -> Unit = { _ -> }
    internal var retry: () -> Unit = {}

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = WeakReference(LayoutInflater.from(parent.context)).get()

        return when (ItemViewType.createByOrdinal(viewType)) {
            ItemViewType.NORMAL -> {
                val binding = ItemUserBinding.inflate(inflater!!, parent, false)
                UserViewHolder(binding.root)
            }
            ItemViewType.LOADING -> {
                val binding = ItemLoadingBinding.inflate(inflater!!, parent, false)
                LoadingViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.binding?.model = getItem(position)
                holder.binding?.root?.setOnClickListener {
                    click(holder.binding.model)
                }
            }
            is LoadingViewHolder -> {
                holder.binding?.model = Loading(state)
                holder.binding?.buttonRetry?.setOnClickListener {
                    retry()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) ItemViewType.NORMAL.ordinal else State.LOADING.ordinal
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasLoader()) 1 else 0
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(itemCount)
    }

    private fun hasLoader(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    enum class ItemViewType {
        NORMAL, LOADING;

        companion object {
            fun createByOrdinal(ordinal: Int): ItemViewType {
                return when (ordinal) {
                    NORMAL.ordinal -> NORMAL
                    LOADING.ordinal -> LOADING
                    else -> NORMAL
                }
            }
        }
    }
}