package com.andyshon.flickrapi.ui.search

import com.andyshon.flickrapi.data.entity.SearchItem
import com.andyshon.flickrapi.data.model.photos.PhotosModel
import com.andyshon.flickrapi.data.repository.local.RealmService
import com.andyshon.flickrapi.ui.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val model: PhotosModel,
    private val repository: RealmService
) : BasePresenter<SearchContract.View>() {

    val photos : ArrayList<SearchItem> = arrayListOf()

    fun search(query: String) {
        model.searchPhotos(query)
            .compose(applyProgressSingle())
            .map {
                if (it.photos.photo.isEmpty()) {
                    view?.empty()
                    return@map
                }
                it.photos.photo.forEach { photo ->
                    photo.urlOriginal?.let {
                        repository.addSearchItem(photo.urlOriginal, query,
                            success = { newId ->
                                photos.add(SearchItem(newId, photo.urlOriginal, query))
                                view?.searched()
                            },
                            error = {
                                view?.error(it)
                            })
                        return@map
                    }
                }
            }
            .subscribe({}, { view?.showMessage(it?.message?:"Error") })
            .addTo(destroyDisposable)
    }

    fun loadHistory() {
        photos.addAll(repository.getSearchItems())
        view?.searched()
    }

    fun deleteItem(pos: Int) {
        val id = photos[pos].id
        repository.deleteSearchItemById(id)
    }

}