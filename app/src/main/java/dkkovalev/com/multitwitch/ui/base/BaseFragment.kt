package dkkovalev.com.multitwitch.ui.base

import android.app.Fragment
import android.os.Bundle
import dkkovalev.com.multitwitch.dagger.ActivityComponent
import dkkovalev.com.multitwitch.dagger.module.ActivityModule
import dkkovalev.com.multitwitch.ui.utils.ConfigPersistentDelegate

open class BaseFragment : Fragment() {
    val configPersistentDelegate = ConfigPersistentDelegate()
    lateinit var activityComponent: ActivityComponent

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        configPersistentDelegate.onSaveInstanceState(outState ?: return)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configPersistentDelegate.onCreate(activity, savedInstanceState)
        activityComponent = configPersistentDelegate.component + ActivityModule(activity)
        super.onCreate(savedInstanceState)
    }
}