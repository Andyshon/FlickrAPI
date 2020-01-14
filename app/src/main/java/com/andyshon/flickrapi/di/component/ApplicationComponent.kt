package com.andyshon.flickrapi.di.component

import com.andyshon.flickrapi.App
import com.andyshon.flickrapi.di.module.ApiModule
import com.andyshon.flickrapi.di.module.ApplicationModule
import com.andyshon.flickrapi.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ApiModule::class])
interface ApplicationComponent {

    fun plusPresentationComponent(): PresentationComponent.Builder

    fun inject(app: App)

}