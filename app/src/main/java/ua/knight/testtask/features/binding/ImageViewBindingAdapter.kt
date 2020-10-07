package ua.knight.testtask.features.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import ua.knight.testtask.core.glide.GlideImage

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:loadInto")
    fun loadInto(view: ImageView, image: GlideImage?) {
        loadInto(view, image, false)
    }

    @JvmStatic
    @BindingAdapter(value = ["app:loadInto", "app:rounded"])
    fun loadInto(view: ImageView, image: GlideImage?, rounded: Boolean) {
        val requestOptions = RequestOptions()
        if (rounded)
            requestOptions.circleCrop()

        image?.loadInto(view, customRequestOptions = requestOptions)
    }
}