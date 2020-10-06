package ua.knight.testtask.features.view.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.knight.testtask.core.platform.recycler.BaseRecyclerListAdapter
import ua.knight.testtask.core.platform.recycler.RecyclerCollectionAdapter
import ua.knight.testtask.databinding.ItemUserBinding
import ua.knight.testtask.features.model.user.User
import java.lang.ref.WeakReference

class UsersRvAdapter : BaseRecyclerListAdapter<User, UsersRvAdapter.UserViewHolder> {

    constructor() : super()
    constructor(list: MutableList<User>?) : super(list)
    constructor(
        list: MutableList<User>?,
        onItemClickListener: RecyclerCollectionAdapter.OnItemClickListener<User>?
    ) : super(list, onItemClickListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = WeakReference(LayoutInflater.from(parent.context)).get()
        val binding = ItemUserBinding.inflate(inflater!!, parent, false)
        return UserViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding?.model = mList[position]
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding : ItemUserBinding? = DataBindingUtil.bind(itemView)
    }
}