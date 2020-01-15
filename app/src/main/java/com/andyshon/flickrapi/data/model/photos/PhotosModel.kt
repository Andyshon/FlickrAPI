package com.andyshon.flickrapi.data.model.photos

import com.andyshon.flickrapi.BuildConfig
import com.andyshon.flickrapi.Constants
import com.andyshon.flickrapi.data.BaseModel
import com.andyshon.flickrapi.data.network.response.PhotosResponse
import com.andyshon.flickrapi.utils.extensions.applySchedulers
import io.reactivex.Single
import javax.inject.Inject

class PhotosModel @Inject constructor(
    private val service: PhotosService
): BaseModel() {

    fun searchPhotos(query: String): Single<PhotosResponse> {
        return service.getPhotos(
            query,
            Constants.KEY_METHOD,
            BuildConfig.API_KEY,
            Constants.KEY_FORMAT,
            Constants.KEY_EXTRAS,
            Constants.KEY_NO_JSON_CALLBACK
        )
            .checkApiErrorSingle()
            .applySchedulers()
    }

}