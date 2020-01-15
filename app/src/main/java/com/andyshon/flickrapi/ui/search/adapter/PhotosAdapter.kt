package com.andyshon.flickrapi.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andyshon.flickrapi.data.entity.SearchItem
import com.andyshon.flickrapi.ui.base.recycler.ItemClickListener

class PhotosAdapter(
    private var items: ArrayList<SearchItem>,
    private val listener: ItemClickListener<SearchItem>
) : RecyclerView.Adapter<PhotosVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosVH {
        return PhotosVH(parent).apply {
            itemView.setOnLongClickListener {
                listener.onItemClick(it, adapterPosition, items[adapterPosition])
                true
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PhotosVH, position: Int) = holder.bind(items[position])

    fun removeObject(position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}