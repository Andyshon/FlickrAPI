package com.andyshon.flickrapi.ui.search

import com.andyshon.flickrapi.ui.base.BaseContract

interface SearchContract : BaseContract {

    interface View : BaseContract.View {
        fun searched()
        fun empty()
        fun error(s:String)
    }
}