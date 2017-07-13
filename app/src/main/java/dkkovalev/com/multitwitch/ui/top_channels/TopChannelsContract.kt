package dkkovalev.com.multitwitch.ui.top_channels

import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import dkkovalev.com.multitwitch.ui.base.BasePresenter
import dkkovalev.com.multitwitch.ui.base.BaseView

interface TopChannelsContract {
    interface View : BaseView {
        fun showTopChannels(topChannels: TopStreams)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopChannels(game:String)
    }
}