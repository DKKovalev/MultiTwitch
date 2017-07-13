package dkkovalev.com.multitwitch.ui.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.ImmediateThinScheduler

interface SchedulerContract {
    val io: Scheduler
    val ui: Scheduler
}

object DefaultScheduler : SchedulerContract {
    override val io: Scheduler = Schedulers.io()
    override val ui: Scheduler = AndroidSchedulers.mainThread()
}

object ImmediateScheduler : SchedulerContract {
    val scheduler: Scheduler = ImmediateThinScheduler.INSTANCE
    override val io: Scheduler = scheduler
    override val ui: Scheduler = scheduler
}
