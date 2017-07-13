package dkkovalev.com.multitwitch.ui.base

interface BasePresenter<in V : BaseView>{
    fun attachView(view:V)
    fun detachView(view:V)
    fun unsubscribe()
}