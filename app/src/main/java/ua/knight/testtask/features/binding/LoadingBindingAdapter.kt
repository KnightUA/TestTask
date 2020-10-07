package ua.knight.testtask.features.binding

import android.view.View
import androidx.databinding.BindingAdapter
import ua.knight.testtask.features.model.loading.State

object LoadingBindingAdapter {

    @JvmStatic
    @BindingAdapter("loading")
    fun loading(view : View, state : State) {
        view.visibility = if(state == State.LOADING) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("error")
    fun error(view : View, state : State) {
        view.visibility = if(state == State.ERROR) View.VISIBLE else View.GONE
    }

}