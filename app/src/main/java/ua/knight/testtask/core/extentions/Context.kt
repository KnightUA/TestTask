package ua.knight.testtask.core.extentions

import android.content.Context
import android.net.ConnectivityManager

val Context.connectivityManager: ConnectivityManager? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)