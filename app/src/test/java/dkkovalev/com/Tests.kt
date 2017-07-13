package dkkovalev.com

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import dkkovalev.com.multitwitch.data.DataSource
import dkkovalev.com.multitwitch.data.models.top_games.Box
import dkkovalev.com.multitwitch.data.models.top_games.Game
import dkkovalev.com.multitwitch.data.models.top_games.Top
import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.exception.NoConnectException
import dkkovalev.com.multitwitch.ui.top_games.TopGamesContract
import dkkovalev.com.multitwitch.ui.top_games.TopGamesPresenter
import dkkovalev.com.multitwitch.ui.utils.ImmediateScheduler
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test


class Tests {

    lateinit var view: TopGamesContract.View
    lateinit var dataSource: DataSource
    lateinit var presenter: TopGamesContract.Presenter

    @Before
    fun setup() {
        view = mock()
        dataSource = mock()
        presenter = TopGamesPresenter(dataSource, ImmediateScheduler)
        presenter.attachView(view)
    }

    @Test
    fun should_alert_no_connectivity() {
        whenever(dataSource.getTopGames())
                .thenReturn(Observable.error(NoConnectException))

        presenter.getTopGames()

        verify(dataSource).getTopGames()
    }

    @Test
    fun should_show_info() {
        val info = TopGames(1, listOf(Top(Game("foo", Box("link1", "link2", "link3")))))
        whenever(dataSource.getTopGames()).thenReturn(Observable.just(info))
        presenter.getTopGames()
        verify(view).showTopGames(info)
        verify(dataSource).getTopGames()
    }

}