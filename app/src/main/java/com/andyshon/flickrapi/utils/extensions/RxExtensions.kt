package com.andyshon.flickrapi.utils.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())