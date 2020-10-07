package ua.knight.testtask.features.binding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("invisibleUnless")
    fun invisibleUnless(view: View, predicate: Boolean) {
        view.visibility = if(predicate) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun goneUnless(view: View, predicate: Boolean) {
        view.visibility = if(predicate) View.VISIBLE else View.GONE
    }

}