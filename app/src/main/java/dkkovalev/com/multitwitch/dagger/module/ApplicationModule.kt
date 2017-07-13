package dkkovalev.com.multitwitch.dagger.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dkkovalev.com.multitwitch.MainApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: MainApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = app

    @Provides
    @Singleton
    fun providesApplication():MainApplication = app

    @Provides
    @Singleton
    fun providesGson():Gson = GsonBuilder().serializeNulls().create()
}
