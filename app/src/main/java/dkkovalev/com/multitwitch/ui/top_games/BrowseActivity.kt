package dkkovalev.com.multitwitch.ui.top_games

import android.app.FragmentTransaction
import android.os.Bundle
import dkkovalev.com.multitwitch.R
import dkkovalev.com.multitwitch.ui.base.BaseActivity

class BrowseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, TopGamesFragment.newInstance(), TopGamesFragment::class.java.simpleName)
        fragmentTransaction.commitAllowingStateLoss()
    }
}
