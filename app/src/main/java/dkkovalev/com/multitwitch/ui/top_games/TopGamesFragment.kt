package dkkovalev.com.multitwitch.ui.top_games

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dkkovalev.com.multitwitch.R
import dkkovalev.com.multitwitch.data.models.top_games.Top
import dkkovalev.com.multitwitch.data.models.top_games.TopGames
import dkkovalev.com.multitwitch.ui.base.BaseFragment
import dkkovalev.com.multitwitch.ui.top_channels.TopChannelsFragment
import dkkovalev.com.multitwitch.ui.utils.OnRecyclerItemClickListener
import dkkovalev.com.multitwitch.ui.utils.RecyclerAdapter
import javax.inject.Inject

class TopGamesFragment : BaseFragment(), TopGamesContract.View, OnRecyclerItemClickListener {

    lateinit var recyclerTopGames: RecyclerView
    lateinit var topGamesList: ArrayList<Top>

    companion object {
        fun newInstance(): TopGamesFragment {
            return TopGamesFragment()
        }
    }

    override fun onClick(viewHolder: RecyclerView.ViewHolder, view: View, pos: Int) {
        presenter.getTopChannels(pos, topGamesList)
    }

    override fun showTopChannels(game: String) {
        Log.i("tag", game)
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()
                .addToBackStack(TopChannelsFragment::class.java.simpleName)
                .replace(R.id.fragment_container, TopChannelsFragment.newInstance(game), TopChannelsFragment::class.java.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun showTopGames(topGames: TopGames) {
        Log.i("tag", topGames.top[0].game.box.large)
        topGamesList = topGames.top as ArrayList<Top>
        val items: ArrayList<Any> = ArrayList()
        items.addAll(topGamesList)
        val adapter: RecyclerAdapter = RecyclerAdapter(activity, this, items)
        recyclerTopGames.adapter = adapter
    }

    @Inject
    lateinit var presenter: TopGamesContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_twitch_top_games, container, false)
        recyclerTopGames = view.findViewById(R.id.recycler_top_games)
        recyclerTopGames.layoutManager = GridLayoutManager(activity, 2)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityComponent.inject(this)
        presenter.attachView(this)
        if (savedInstanceState == null) {
            presenter.getTopGames()
        }
    }
}