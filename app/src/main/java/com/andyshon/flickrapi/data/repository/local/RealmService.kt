package com.andyshon.flickrapi.data.repository.local

import com.andyshon.flickrapi.data.entity.SearchItem
import io.realm.Realm
import io.realm.RealmResults

class RealmService(private val mRealm: Realm) {

    fun getSearchItems(): RealmResults<SearchItem> {
        return mRealm.where(SearchItem::class.java).findAll()
    }

    fun addSearchItem(image: String, title: String,
                      success: (id: Int) -> Unit, error: (s: String) -> Unit) {

        // increment index
        val num = mRealm.where(SearchItem::class.java).max("id")
        val id = if (num == null) 1 else num.toInt() + 1

        mRealm.executeTransactionAsync ({
            val searchItem = it.createObject(SearchItem::class.java, id)
            searchItem.image = image
            searchItem.title = title
        },{
            success.invoke(id)
        },{
            error.invoke(it.message?:"Error")
        })
    }

    fun deleteSearchItemById(id: Int) {
        mRealm.executeTransaction { realm ->
            val result = realm.where(SearchItem::class.java).equalTo("id", id).findAll()
            result.deleteAllFromRealm()
        }
    }
    
}