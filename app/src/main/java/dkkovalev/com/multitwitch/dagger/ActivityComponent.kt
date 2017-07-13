package dkkovalev.com.multitwitch.dagger

import dagger.Subcomponent
import dkkovalev.com.multitwitch.ui.top_games.BrowseActivity
import dkkovalev.com.multitwitch.dagger.module.ActivityModule
import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import dkkovalev.com.multitwitch.ui.top_channels.TopChannelsFragment
import dkkovalev.com.multitwitch.ui.top_games.TopGamesFragment

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: BrowseActivity)
    fun inject(fragment: TopGamesFragment)
    fun inject(fragment: TopChannelsFragment)
}
