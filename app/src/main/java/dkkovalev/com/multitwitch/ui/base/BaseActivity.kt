package dkkovalev.com.multitwitch.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dkkovalev.com.multitwitch.dagger.ActivityComponent
import dkkovalev.com.multitwitch.dagger.module.ActivityModule
import dkkovalev.com.multitwitch.ui.utils.ConfigPersistentDelegate

open class BaseActivity : AppCompatActivity() {
    val configPersistentDelegate = ConfigPersistentDelegate()
    lateinit var activityComponent: ActivityComponent

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        configPersistentDelegate.onSaveInstanceState(outState ?: return)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configPersistentDelegate.onCreate(this, savedInstanceState)
        activityComponent = configPersistentDelegate.component + ActivityModule(this)
        super.onCreate(savedInstanceState)
    }
}
