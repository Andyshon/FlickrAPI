package com.andyshon.flickrapi.data.model.photos

import com.andyshon.flickrapi.data.network.response.PhotosResponse
import io.reactivex.Single
import retrofit2.http.*

interface PhotosService {

    @GET("/services/rest")
    fun getPhotos(
        @Query("text") query: String,
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
        @Query("extras") extras: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Single<PhotosResponse>

}