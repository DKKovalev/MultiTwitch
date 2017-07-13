package dkkovalev.com.multitwitch.extensions

import android.net.ConnectivityManager

val ConnectivityManager.isConnected: Boolean
get() = activeNetworkInfo?.isConnected ?: false
