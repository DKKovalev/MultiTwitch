package dkkovalev.com.multitwitch.ui.top_channels

import dkkovalev.com.multitwitch.data.DataSource
import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import dkkovalev.com.multitwitch.extensions.addToCompositeDisposable
import dkkovalev.com.multitwitch.ui.utils.SchedulerContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class TopChannelsPresenter(private val dataSource: DataSource, private val scheduler: SchedulerContract) : TopChannelsContract.Presenter {

    override fun attachView(view: TopChannelsContract.View) {
        this.view = view
        topChannels?.let { this::showChannels }
        topChannelsObservable?.let { this::subscribeToTopChannels }
    }

    override fun detachView(view: TopChannelsContract.View) {
        this.view = null
        compositeDisposable.clear()
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    override fun getTopChannels(game: String) {
        dataSource.getTopChannels(game).subscribeOn(scheduler.io).observeOn(scheduler.ui).replay().apply {
            subscribeToTopChannels(this)
            connectableDisposable.add(connect())
        }
    }

    fun subscribeToTopChannels(observable: Observable<TopStreams>) {
        topChannelsObservable = observable
        view?.let {
            observable.doOnError { _ -> topChannelsObservable = null }
                    .doOnComplete {
                        topChannelsObservable = null
                    }
                    .subscribe({
                        top ->
                        topChannels = top
                        showChannels(top)
                    }, { e -> {} })
                    .addToCompositeDisposable(compositeDisposable)
        }
    }

    fun showChannels(topChannels: TopStreams) {
        this.topChannels = topChannels
        view?.showTopChannels(topChannels)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val connectableDisposable: CompositeDisposable = CompositeDisposable()

    var view: TopChannelsContract.View? = null
    var topChannels: TopStreams? = null
    var topChannelsObservable: Observable<TopStreams>? = null

}
