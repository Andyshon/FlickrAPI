package com.andyshon.flickrapi

import com.andyshon.flickrapi.di.component.ApplicationComponent
import com.andyshon.flickrapi.di.component.DaggerApplicationComponent
import com.andyshon.flickrapi.di.module.ApplicationModule

class DaggerComponentProvider(val app: App) {

    private var _appComponent: ApplicationComponent? = null

    val appComponent: ApplicationComponent
        get() {
            if (_appComponent == null) {
                _appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(app))
                    .build()
            }
            return _appComponent!!
        }
}