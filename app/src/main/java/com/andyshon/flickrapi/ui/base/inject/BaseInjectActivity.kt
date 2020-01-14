package com.andyshon.flickrapi.ui.base.inject

import com.andyshon.flickrapi.App
import com.andyshon.flickrapi.di.component.PresentationComponent
import com.andyshon.flickrapi.di.module.PresentationModule
import com.andyshon.flickrapi.ui.base.BaseActivity
import com.andyshon.flickrapi.ui.base.BaseContract
import com.andyshon.flickrapi.ui.base.DialogProvider
import javax.inject.Inject

abstract class BaseInjectActivity: BaseActivity() {

    @Inject
    lateinit var dialog: DialogProvider

    val presentationComponent: PresentationComponent by lazy {
        App[this].appComponent.plusPresentationComponent()
            .presentationModule(PresentationModule(this))
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.detachFromView()
        dialog.dismissProgress()
    }

    /**
     * Returns presenter if exists
     * to detach from view on activity destroy()
     */
    protected abstract fun getPresenter(): BaseContract.Presenter<*>?

    override fun showProgress(tag: Any?, message: String?) {
        dialog.showProgress(this)
    }

    override fun hideProgress(tag: Any?) {
        dialog.dismissProgress()
    }
}