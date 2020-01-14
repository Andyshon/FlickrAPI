package com.andyshon.flickrapi.ui.base

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {

    protected val destroyDisposable: CompositeDisposable = CompositeDisposable()

    protected var view: T? = null
        private set

    override fun attachToView(view: T) {
        this.view = view
    }

    override fun detachFromView() {
        if (!destroyDisposable.isDisposed) {
            destroyDisposable.dispose()
        }
        view = null
    }

    protected fun onClearedSubscribe(): Consumer<in Disposable> {
        return Consumer { disposable -> destroyDisposable.add(disposable) }
    }

    protected fun <T> applyProgressObservable(tag: Any? = null): ObservableTransformer<T, T> = ObservableTransformer {
        it.doOnSubscribe { view?.showProgress(tag) }.doFinally { view?.hideProgress(tag) }
    }

    protected fun applyProgressCompletable(tag: Any? = null): CompletableTransformer = CompletableTransformer {
        it.doOnSubscribe { view?.showProgress(tag) }.doFinally { view?.hideProgress(tag) }
    }

    protected fun <T> applyProgressSingle(tag: Any? = null, message: String? = null): SingleTransformer<T, T> = SingleTransformer {
        it.doOnSubscribe { view?.showProgress(tag, message) }.doFinally { view?.hideProgress(tag) }
    }

    protected fun showDefaultErrors(throwable: Throwable?) {
        throwable?.message?.let { view?.showMessage(it) }
    }

    private fun isNetworkError(throwable: Throwable?): Boolean = throwable is SocketException
            || throwable is UnknownHostException
            || throwable is SocketTimeoutException
}