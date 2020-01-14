package com.andyshon.flickrapi.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.andyshon.flickrapi.ui.base.presentation.PresentationComponentProvider
import com.andyshon.flickrapi.ui.base.presentation.PresentationDelegate
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity(), PresentationComponentProvider, BaseContract.View {

    private val presentationDelegate by lazy {
        PresentationDelegate.Factory.create(this)
    }

    override fun onDestroy() {
        presentationDelegate.onDestroy()
        super.onDestroy()
    }

    override fun provideSupportFragmentManager(): FragmentManager {
        return supportFragmentManager
    }

    override fun provideActivity(): FragmentActivity {
        return this
    }

    override fun getDestroyDisposable(): CompositeDisposable {
        return presentationDelegate.getDestroyDisposable()
    }

    override fun showProgress(tag: Any?, message: String?) {
        presentationDelegate.showProgress(tag, message)
    }

    override fun hideProgress(tag: Any?) {
        presentationDelegate.hideProgress(tag)
    }

    override fun showMessage(messageRes: Int, tag: Any?) {
        presentationDelegate.showMessage(messageRes, tag)
    }

    override fun showMessage(message: String, tag: Any?) {
        presentationDelegate.showMessage(message, tag)
    }
}