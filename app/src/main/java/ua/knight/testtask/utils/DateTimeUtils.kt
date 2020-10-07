package ua.knight.testtask.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_OF_BIRTHDAY_PATTERN = "yyyy-mm-dd"

    fun parseIntoMillis(inputDate: String, pattern: String = DEFAULT_DATE_TIME_PATTERN): Long {
        val inputDateFormat = SimpleDateFormat(pattern, Locale.getDefault()).parse(inputDate)
        return inputDateFormat?.time ?: 0L
    }

    fun formatInto(timeInMillis: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(timeInMillis)
    }

    fun formatIntoDateOfBirthday(timeInMillis: Long): String {
        return formatInto(timeInMillis, DATE_OF_BIRTHDAY_PATTERN)
    }

}