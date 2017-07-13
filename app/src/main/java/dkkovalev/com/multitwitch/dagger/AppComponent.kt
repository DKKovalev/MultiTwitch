package dkkovalev.com.multitwitch.dagger

import dagger.Component
import dkkovalev.com.multitwitch.dagger.module.ApiModule
import dkkovalev.com.multitwitch.dagger.module.ApplicationModule
import dkkovalev.com.multitwitch.dagger.module.DataModule
import dkkovalev.com.multitwitch.dagger.module.PresenterModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class, ApiModule::class))
interface AppComponent {
    operator fun plus(presenterModule: PresenterModule): ConfigPersistentComponent
}
