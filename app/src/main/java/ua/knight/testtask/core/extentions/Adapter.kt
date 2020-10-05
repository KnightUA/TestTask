package ua.knight.testtask.core.extentions

import android.view.View
import ua.knight.testtask.core.platform.recycler.RecyclerCollectionAdapter

inline fun <M> RecyclerCollectionAdapter<M>.doOnItemViewClick(crossinline action: (item: M, view: View?) -> Unit) =
    addOnItemClickListener(onItemViewClick = action)

inline fun <M> RecyclerCollectionAdapter<M>.doOnViewClick(crossinline action: (view: View?) -> Unit) =
    addOnItemClickListener(onViewClick = action)

inline fun <M> RecyclerCollectionAdapter<M>.doOnItemClick(crossinline action: (item: M) -> Unit) =
    addOnItemClickListener(onItemClick = action)

inline fun <M> RecyclerCollectionAdapter<M>.addOnItemClickListener(
    crossinline onItemViewClick: (item: M, view: View?) -> Unit = { _, _ -> },
    crossinline onViewClick: (view: View?) -> Unit = { _ -> },
    crossinline onItemClick: (item: M) -> Unit = {}
): RecyclerCollectionAdapter.OnItemClickListener<M> {

    val listener = object : RecyclerCollectionAdapter.OnItemClickListener<M> {
        override fun onItemClick(item: M) {
            onItemClick.invoke(item)
        }

        override fun onItemClick(view: View?) {
            onViewClick.invoke(view)
        }

        override fun onItemClick(item: M, view: View?) {
            onItemViewClick.invoke(item, view)
        }
    }

    setOnItemClickListener(listener)

    return listener
}