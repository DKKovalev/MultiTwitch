package dkkovalev.com.multitwitch.dagger.module

import dagger.Module
import dagger.Provides
import dkkovalev.com.multitwitch.data.DataSource
import dkkovalev.com.multitwitch.data.TopGamesRepository
import dkkovalev.com.multitwitch.data.TwitchApi

import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun providesDataSource(api: TwitchApi): DataSource = TopGamesRepository(api)
}
