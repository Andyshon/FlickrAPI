package com.andyshon.flickrapi.ui.search.adapter

import android.view.ViewGroup
import com.andyshon.flickrapi.R
import com.andyshon.flickrapi.data.entity.SearchItem
import com.andyshon.flickrapi.ui.base.recycler.BaseVH
import com.andyshon.flickrapi.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosVH(parent: ViewGroup) : BaseVH<SearchItem>(parent, R.layout.item_photo) {

    override fun bind(item: SearchItem) {
        itemView.tvTitle.text = item.title
        itemView.image.loadImage(item.image)
    }
}