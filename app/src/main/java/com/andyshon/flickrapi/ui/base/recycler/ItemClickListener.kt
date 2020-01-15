package com.andyshon.flickrapi.ui.base.recycler

import android.view.View

interface ItemClickListener<in M> {
    fun onItemClick(view: View, pos: Int, item: M)
}