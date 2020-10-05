package ua.knight.testtask.core.glide

class GlideImageUrl(private val url : String? = null) : GlideImage {
    override val image: Any?
        get() = url
}