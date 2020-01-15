package com.andyshon.flickrapi.data.entity

import com.google.gson.annotations.SerializedName

data class PhotoInner(
    @SerializedName("id") val id: Long,
    @SerializedName("owner") val owner: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: Int,
    @SerializedName("farm") val farm: Int,
    @SerializedName("title") val title: String,
    @SerializedName("ispublic") val isPublic: Int,
    @SerializedName("isfriend") val isFriend: Int,
    @SerializedName("isfamily") val isFamily: Int,
    @SerializedName("url_o") val urlOriginal: String? = null,
    @SerializedName("height_o") val heightOriginal: String,
    @SerializedName("width_o") val widthOriginal: String
)