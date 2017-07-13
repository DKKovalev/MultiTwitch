package dkkovalev.com.multitwitch

import android.app.Application
import dkkovalev.com.multitwitch.dagger.AppComponent
import dkkovalev.com.multitwitch.dagger.DaggerAppComponent
import dkkovalev.com.multitwitch.dagger.module.ApplicationModule

class MainApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        buildComponent()
    }

    fun buildComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}