package dkkovalev.com.multitwitch.ui.top_games

import dkkovalev.com.multitwitch.data.DataSource
import dkkovalev.com.multitwitch.data.models.top_games.Top
import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.extensions.addToCompositeDisposable
import dkkovalev.com.multitwitch.ui.utils.SchedulerContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class TopGamesPresenter(private val dataSource: DataSource, private val scheduler: SchedulerContract) : TopGamesContract.Presenter {

    override fun attachView(view: TopGamesContract.View) {
        this.view = view
        topGames?.let { this::showGames }
        topGamesObservable?.let { this::subscribeToTopGames }
    }

    override fun detachView(view: TopGamesContract.View) {
        this.view = null
        compositeDisposable.clear()
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    override fun getTopGames() {
        dataSource.getTopGames().subscribeOn(scheduler.io).observeOn(scheduler.ui).replay().apply {
            subscribeToTopGames(this)
            connectableDisposable.add(connect())
        }
    }

    override fun getTopChannels(pos: Int, topGames: ArrayList<Top>) {
        view?.showTopChannels(topGames[pos].game.name)
    }

    fun subscribeToTopGames(observable: Observable<TopGames>) {
        topGamesObservable = observable
        view?.let {
            observable.doOnError { _ -> topGamesObservable = null }
                    .doOnComplete {
                        topGamesObservable = null
                    }
                    .subscribe({
                        top ->
                        topGames = top
                        showGames(top)
                    }, { e -> {} })
                    .addToCompositeDisposable(compositeDisposable)
        }
    }

    fun showGames(topGames: TopGames) {
        this.topGames = topGames
        view?.showTopGames(topGames)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val connectableDisposable: CompositeDisposable = CompositeDisposable()

    var view: TopGamesContract.View? = null
    var topGames: TopGames? = null
    var topGamesObservable: Observable<TopGames>? = null

}
