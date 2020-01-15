package com.andyshon.flickrapi.ui.base.presentation

import androidx.annotation.StringRes
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm

interface PresentationDelegate {

    fun onDestroy()

    fun showMessage(message: String, tag: Any? = null)

    fun showMessage(@StringRes messageRes: Int, tag: Any? = null)

    fun showProgress(tag: Any? = null, message: String? = null)

    fun hideProgress(tag: Any? = null)

    fun getDestroyDisposable(): CompositeDisposable

    fun getRealm(): Realm

    class Factory {

        companion object {

            fun create(provider: PresentationComponentProvider): PresentationDelegate = PresentationDelegateImpl(provider)

        }
    }
}