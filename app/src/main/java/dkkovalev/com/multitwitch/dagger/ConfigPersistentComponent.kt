package dkkovalev.com.multitwitch.dagger

import dagger.Subcomponent
import dkkovalev.com.multitwitch.dagger.module.ActivityModule
import dkkovalev.com.multitwitch.dagger.module.PresenterModule

@ConfigPersistent
@Subcomponent(modules = arrayOf(PresenterModule::class))
interface ConfigPersistentComponent {
    operator fun plus(activityModule: ActivityModule): ActivityComponent
}
