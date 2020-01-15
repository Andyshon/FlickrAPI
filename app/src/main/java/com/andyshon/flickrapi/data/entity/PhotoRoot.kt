package com.andyshon.flickrapi.data.entity

import com.google.gson.annotations.SerializedName

data class PhotoRoot(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photo: ArrayList<PhotoInner>
)