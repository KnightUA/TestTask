package ua.knight.testtask.features.view.adapter.holders.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.knight.testtask.databinding.ItemLoadingBinding

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = DataBindingUtil.bind<ItemLoadingBinding>(itemView)
}