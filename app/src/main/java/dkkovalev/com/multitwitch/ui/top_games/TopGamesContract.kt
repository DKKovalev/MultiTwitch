package dkkovalev.com.multitwitch.ui.top_games

import dkkovalev.com.multitwitch.data.models.top_games.Top
import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.ui.base.BasePresenter
import dkkovalev.com.multitwitch.ui.base.BaseView

interface TopGamesContract {
    interface View : BaseView {
        fun showTopGames(topGames: TopGames)
        fun showTopChannels(game: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopGames()
        fun getTopChannels(pos: Int, topGames: ArrayList<Top>)
    }
}
