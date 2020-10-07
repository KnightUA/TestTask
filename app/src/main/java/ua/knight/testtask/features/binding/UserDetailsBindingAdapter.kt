package ua.knight.testtask.features.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ua.knight.testtask.utils.DateTimeUtils

object UserDetailsBindingAdapter {

    @JvmStatic
    @BindingAdapter("dateOfBirthday")
    fun dateOfBirthday(view : TextView, timeInMillis:Long?) {
        timeInMillis?.let {
            view.text = DateTimeUtils.formatIntoDateOfBirthday(it)
        }
    }

}