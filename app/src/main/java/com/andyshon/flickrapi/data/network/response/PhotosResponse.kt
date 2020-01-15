package com.andyshon.flickrapi.data.network.response

import com.andyshon.flickrapi.data.entity.PhotoRoot
import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photos") val photos: PhotoRoot
)