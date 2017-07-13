package dkkovalev.com.multitwitch.dagger.module

import dagger.Module
import dagger.Provides
import dkkovalev.com.multitwitch.data.DataSource
import dkkovalev.com.multitwitch.ui.top_channels.TopChannelsContract
import dkkovalev.com.multitwitch.ui.top_channels.TopChannelsPresenter
import dkkovalev.com.multitwitch.ui.utils.DefaultScheduler
import dkkovalev.com.multitwitch.ui.top_games.TopGamesContract
import dkkovalev.com.multitwitch.ui.top_games.TopGamesPresenter

@Module
class PresenterModule {
    @Provides
    fun providesTopGamesPresenter(dataSource: DataSource): TopGamesContract.Presenter = TopGamesPresenter(dataSource, DefaultScheduler)

    @Provides
    fun providesTopChannelsPresenter(dataSource: DataSource): TopChannelsContract.Presenter = TopChannelsPresenter(dataSource, DefaultScheduler)
}
