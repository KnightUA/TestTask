package ua.knight.testtask.features.view.adapter.holders.user

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.knight.testtask.databinding.ItemUserBinding

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = DataBindingUtil.bind<ItemUserBinding>(itemView)
}