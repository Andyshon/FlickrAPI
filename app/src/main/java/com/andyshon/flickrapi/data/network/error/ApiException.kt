package com.andyshon.flickrapi.data.network.error

import com.google.gson.annotations.SerializedName

data class ApiException(@SerializedName("error") val error: String) : Throwable()