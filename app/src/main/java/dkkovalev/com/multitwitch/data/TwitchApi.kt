package dkkovalev.com.multitwitch.data

import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchApi {
    @GET("games/top")
    fun getTopGames(@Header("Client-ID") clientId: String): Observable<TopGames>

    @GET("streams")
    fun getTopChannels(@Header("Client-ID") clientId: String, @Query("game") game: String): Observable<TopStreams>
}
