package com.andyshon.flickrapi.data

import com.andyshon.flickrapi.data.network.error.ApiException
import com.google.gson.JsonParser
import io.reactivex.*
import retrofit2.HttpException

abstract class BaseModel {

    companion object {
        private const val FIELD_ERRORS = "error"
    }

    protected fun <T> Single<T>.checkApiErrorSingle(): Single<T> =
        onErrorResumeNext { throwable ->
            parseHttpExceptionSingle(throwable)
        }

    private fun <T> parseHttpExceptionSingle(throwable: Throwable): Single<T> {
        if (throwable is ApiException) {
            return Single.error(throwable)
        }
        if (throwable !is HttpException) {
            return Single.error(throwable)
        }
        val error = getError(throwable)
        if (error != null) {
            return Single.error(ApiException(error))
        }

        return Single.error(throwable)
    }

    private fun getError(throwable: Throwable): String? {
        val parser = JsonParser()
        val body = parser.parse((throwable as HttpException).response().errorBody()?.string()).asJsonObject
        if (body.has(FIELD_ERRORS)) {
            return body.get(FIELD_ERRORS).asString
        }
        return null
    }
}