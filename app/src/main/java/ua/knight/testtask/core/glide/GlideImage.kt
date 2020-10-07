package ua.knight.testtask.core.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.Serializable

interface GlideImage : Serializable {
    val image: Any?

    /**
     * Notice usage of placeholder will override current requestOptions
     */
    fun loadInto(
        imageView: ImageView,
        placeholder: Drawable? = null,
        customRequestOptions: RequestOptions? = null
    ) {
        if (image == null) return

        val requestOptions = customRequestOptions ?: RequestOptions()

        placeholder?.let { requestOptions.placeholder(it) }

        Glide.with(imageView.context)
            .load(image)
            .apply(requestOptions)
            .into(imageView)
    }
}