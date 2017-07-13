package dkkovalev.com.multitwitch.data

import dkkovalev.com.multitwitch.BuildConfig
import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import io.reactivex.Observable

interface DataSource {
    fun getTopGames(): Observable<TopGames>
    fun getTopChannels(game: String): Observable<TopStreams>
}

class TopGamesRepository(private val api: TwitchApi) : DataSource {
    override fun getTopGames(): Observable<TopGames> = api.getTopGames(BuildConfig.CLIENT_ID)
    override fun getTopChannels(game:String): Observable<TopStreams> = api.getTopChannels(BuildConfig.CLIENT_ID, game)
}
