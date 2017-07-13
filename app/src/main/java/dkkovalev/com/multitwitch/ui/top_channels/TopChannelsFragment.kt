package dkkovalev.com.multitwitch.ui.top_channels

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dkkovalev.com.multitwitch.R
import dkkovalev.com.multitwitch.data.models.top_streams.Stream
import dkkovalev.com.multitwitch.data.models.top_streams.TopStreams
import dkkovalev.com.multitwitch.ui.base.BaseFragment
import dkkovalev.com.multitwitch.ui.utils.OnRecyclerItemClickListener
import dkkovalev.com.multitwitch.ui.utils.RecyclerAdapter
import javax.inject.Inject

class TopChannelsFragment : BaseFragment(), TopChannelsContract.View, OnRecyclerItemClickListener {
    override fun onClick(viewHolder: RecyclerView.ViewHolder, view: View, pos: Int) {
    }

    lateinit var recyclerTopChannels: RecyclerView

    companion object {
        fun newInstance(game: String): TopChannelsFragment {
            val fragment: TopChannelsFragment = TopChannelsFragment()
            val args: Bundle = Bundle()
            args.putString("game", game)
            fragment.arguments = args
            return fragment
        }
    }

    override fun showTopChannels(topChannels: TopStreams) {
        Log.i("tag", topChannels.streams[0].channel.name)
        val tops: ArrayList<Stream> = topChannels.streams as ArrayList<Stream>
        val items: ArrayList<Any> = ArrayList()
        items.addAll(tops)
        val adapter: RecyclerAdapter = RecyclerAdapter(activity, this, items)
        recyclerTopChannels.adapter = adapter
    }

    @Inject
    lateinit var presenter: TopChannelsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_twitch_top_games, container, false)
        recyclerTopChannels = view.findViewById(R.id.recycler_top_games)
        recyclerTopChannels.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityComponent.inject(this)
        presenter.attachView(this)
        if (savedInstanceState == null) {
            presenter.getTopChannels(arguments.getString("game"))
        }
    }
}
