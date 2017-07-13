package dkkovalev.com.multitwitch.extensions

import android.content.Context
import dkkovalev.com.multitwitch.MainApplication

val Context.appComponent
    get() = (applicationContext as MainApplication).appComponent
