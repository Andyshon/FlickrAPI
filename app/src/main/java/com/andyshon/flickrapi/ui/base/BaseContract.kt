package com.andyshon.flickrapi.ui.base

import androidx.annotation.StringRes

interface BaseContract {

    interface View {

        fun showMessage(message: String, tag: Any? = null)

        fun showMessage(@StringRes messageRes: Int, tag: Any? = null)

        fun showProgress(tag: Any? = null, message: String? = null)

        fun hideProgress(tag: Any? = null)

    }

    interface Presenter<in V : View> {

        fun attachToView(view: V)

        fun detachFromView()

    }

}