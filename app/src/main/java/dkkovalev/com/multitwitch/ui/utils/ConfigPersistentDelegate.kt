package dkkovalev.com.multitwitch.ui.utils

import android.content.Context
import android.os.Bundle
import dkkovalev.com.multitwitch.dagger.ConfigPersistentComponent
import dkkovalev.com.multitwitch.dagger.module.PresenterModule
import dkkovalev.com.multitwitch.extensions.appComponent
import java.util.concurrent.atomic.AtomicLong

class ConfigPersistentDelegate {
    companion object {
        private const val KEY_ID = "KEY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    private var id: Long = 0
    var instanceSaved = false
    lateinit var component: ConfigPersistentComponent
        get

    fun onCreate(context: Context, savedInstantState: Bundle?) {
        id = savedInstantState?.getLong(KEY_ID) ?: NEXT_ID.getAndIncrement()
        component = componentsMap.getOrPut(id, { context.appComponent + PresenterModule() })
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(KEY_ID, id)
        instanceSaved = true
    }

    fun onDestroy() {
        if (!instanceSaved) {
            componentsMap.remove(id)
        } else {

        }
    }
}
