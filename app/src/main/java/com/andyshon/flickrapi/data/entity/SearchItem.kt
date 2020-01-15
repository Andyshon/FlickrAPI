package com.andyshon.flickrapi.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SearchItem(
    @PrimaryKey var id: Int,
    var image: String,
    var title: String
) : RealmObject() {
    constructor() : this(0, "", "")
}