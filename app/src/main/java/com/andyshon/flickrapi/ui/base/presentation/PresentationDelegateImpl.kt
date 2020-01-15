package com.andyshon.flickrapi.ui.base.presentation

import com.andyshon.flickrapi.R
import com.andyshon.flickrapi.ui.widget.dialog.MessageDialog
import com.andyshon.flickrapi.ui.widget.dialog.ProgressDialog
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.lang.ref.WeakReference

class PresentationDelegateImpl(provider: PresentationComponentProvider) : PresentationDelegate {

    private val providerRef = WeakReference(provider)
    private var lastTime = 0L

    private val disposable by lazy {
        CompositeDisposable()
    }

    private val mRealm by lazy {
        Realm.getDefaultInstance()
    }

    private val progressDialog by lazy {
        ProgressDialog.newInstance(provider.provideActivity().getString(R.string.dialog_action_loading))
    }

    override fun getRealm(): Realm {
        return mRealm
    }

    override fun onDestroy() {
        disposable.clear()
        mRealm.close()
    }

    override fun showMessage(messageRes: Int, tag: Any?) {
        if (providerRef.get() != null) {
            showMessage(providerRef.get()!!.provideActivity().getString(messageRes), tag)
        }
    }

    override fun showMessage(message: String, tag: Any?) {
        hideProgress()

        if (providerRef.get() != null && System.currentTimeMillis() - lastTime > 100) {
            lastTime = System.currentTimeMillis()
            MessageDialog.newInstance(message).show(providerRef.get()!!.provideSupportFragmentManager())
        }
    }

    override fun showProgress(tag: Any?, message: String?) {
        if (providerRef.get() != null && !progressDialog.isAdded) {
            if (message != null) {
                progressDialog.setDescription(message)
            }

            if (!progressDialog.isShowing) {
                progressDialog.show(providerRef.get()!!.provideSupportFragmentManager())
            }
        }
    }

    override fun hideProgress(tag: Any?) {
        try {
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
        } catch (e: Exception) {
        }
    }

    override fun getDestroyDisposable(): CompositeDisposable {
        return disposable
    }

}